package com.challenge.partobita.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {


    override fun intercept(chain: Interceptor.Chain?): Response {
        var request = chain?.request()
            ?.newBuilder()
            ?.header("Content-Type", "application/json")
            ?.build()
        return chain?.proceed(request)!!
    }


}