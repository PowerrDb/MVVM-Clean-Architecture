package com.challenge.partobita.di


import android.annotation.SuppressLint
import android.util.Log
import com.orhanobut.hawk.Hawk
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationIAuthenticator @Inject constructor(private val api: APIServiceHolder) :
    Authenticator {


    @SuppressLint("CheckResult")
    override fun authenticate(route: Route, response: Response): Request? {
        if (api== null) {
            //we cannot answer the challenge as no token service is available

            return null //as per contract of Retrofit Authenticator interface for when unable to contest a challenge
        }
        Log.e("___","aaaaa")
        val token = api.apiService()!!.getNewAccessToken().execute().body()!!.token
        Hawk.put("token", token)
        return response.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
    }

}