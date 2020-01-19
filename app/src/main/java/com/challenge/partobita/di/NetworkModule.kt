package com.challenge.partobita.di

import android.content.Context
import com.challenge.cafebazaar.di.qualifier.ApplicationContext
import com.challenge.data.api.ListCharacterApi
import com.challenge.partobita.BuildConfig
import com.challenge.presentation.common.ConnectionStateLiveData
import dagger.Module
import dagger.Provides
import dagger.Reusable
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
    fun providesOkHttpClient(
        authenticationInterceptor: AuthenticationInterceptor,
        okHttpClientBuilder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        okHttpClientBuilder.addInterceptor(authenticationInterceptor)

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder
            .connectTimeout(20L, TimeUnit.SECONDS)
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS).build()

    }




    @Provides
    @Reusable
    internal fun provideListCharacterApi(retrofit: Retrofit): ListCharacterApi {
        return retrofit.create(ListCharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://play.hen-dev.ir/v1/Character/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideInternetCheck(@ApplicationContext context: Context) = ConnectionStateLiveData(context)



}