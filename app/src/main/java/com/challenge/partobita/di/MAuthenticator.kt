package com.challenge.partobita.di


import android.annotation.SuppressLint
import com.orhanobut.hawk.Hawk
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MAuthenticator @Inject constructor(private val api: APIServiceHolder) :
    Authenticator {


    @SuppressLint("CheckResult")
    override fun authenticate(route: Route, response: Response): Request? {
        if (api == null) {
            return null
        }
        val token = api.apiService()!!.getNewAccessToken().execute().body()!!.token
        Hawk.put("token", token)
        return response.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
    }

}