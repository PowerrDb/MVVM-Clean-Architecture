package com.challenge.partobita.di

import com.challenge.data.api.MApi
import com.challenge.partobita.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
    fun providesOkHttpClient(
        authenticationInterceptor: AuthenticationInterceptor,
        okHttpClientBuilder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authenticationAuthenticator: MAuthenticator
    ): OkHttpClient {

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