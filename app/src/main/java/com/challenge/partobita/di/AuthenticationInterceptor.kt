package com.challenge.partobita.di

import android.util.Log
import com.orhanobut.hawk.Hawk
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {




    override fun intercept(chain: Interceptor.Chain?): Response {
        var request = chain?.request()
            ?.newBuilder()
            ?.header("Authorization", "Bearer ${Hawk.get("token","")}")
            ?.header("Content-Type", "application/json")
            ?.build()
        return chain?.proceed(request)!!
    }


}