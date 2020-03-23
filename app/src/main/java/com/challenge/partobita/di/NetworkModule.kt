package com.challenge.partobita.di

import android.app.Application
import android.content.Context
import android.net.http.HttpResponseCache
import com.challenge.cafebazaar.di.qualifier.ApplicationContext
import com.challenge.data.api.MApi
import com.challenge.partobita.App
import com.challenge.partobita.BuildConfig
import com.challenge.presentation.common.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



@Module
open class NetworkModule {

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    fun provideHttpAuthenticator(apiServiceHolder :APIServiceHolder): Authenticator = MAuthenticator(apiServiceHolder)


    @Provides
    @Singleton
    fun provideCache(application: App): Cache {
        val cacheSize = 10 * 1024 * 1024.toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http_cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        cache: Cache,
        authenticationInterceptor: AuthenticationInterceptor,
        okHttpClientBuilder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authenticationAuthenticator: MAuthenticator
    ): OkHttpClient {

        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.retryOnConnectionFailure(true)

        okHttpClientBuilder.addInterceptor(Interceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()

            if (!NetworkUtils.instance.hasNetwork(context)) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(30, TimeUnit.DAYS)
                    .maxAge(30, TimeUnit.DAYS)
                    .onlyIfCached()
                    .build()

                requestBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .cacheControl(cacheControl)
//                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
            }

            val request = requestBuilder.build()

            return@Interceptor chain.proceed(request)
        })

        okHttpClientBuilder.addNetworkInterceptor(Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val responseBuilder = response.newBuilder()

            val cacheControl: CacheControl = if (NetworkUtils.instance.hasNetwork(context)) {
                CacheControl.Builder()
                    .maxAge(60, TimeUnit.SECONDS)
                    .maxStale(60, TimeUnit.SECONDS)
                    .onlyIfCached()
                    .build()
            } else {
                CacheControl.Builder()
                    .maxAge(7, TimeUnit.DAYS)
                    .maxStale(7, TimeUnit.DAYS)
                    .onlyIfCached()
                    .build()
            }

            responseBuilder
                .removeHeader("Cache-Control")
                .removeHeader("Pragma")
                .addHeader(
                    "Cache-Control",
                    cacheControl.toString()
                )
                .build()



            return@Interceptor responseBuilder.build()
        })

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClientBuilder.authenticator(authenticationAuthenticator)
        okHttpClientBuilder.addInterceptor(authenticationInterceptor)

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder
            .connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS).build()

    }





    @Provides
    @Reusable
    internal fun provideListCharacterApi(retrofit: Retrofit,apiHolder: APIServiceHolder): MApi {
        val api =retrofit.create(MApi::class.java)
        apiHolder.setAPIService(api)
        return  api
    }




    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://play.hen-dev.ir/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()



    @Provides
    @Singleton
    fun apiServiceHolder(): APIServiceHolder {
        return APIServiceHolder()
    }

}