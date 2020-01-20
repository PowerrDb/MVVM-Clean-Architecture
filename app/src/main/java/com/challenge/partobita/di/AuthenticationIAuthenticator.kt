package com.challenge.partobita.di


import android.annotation.SuppressLint
import android.util.Log
import com.challenge.data.extention.applyIoScheduler
import com.challenge.data.extention.defaultErrorHandler
import com.challenge.domain.models.AuthBodyModel
import com.challenge.domain.models.AuthResponseModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject
import javax.inject.Singleton
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import java.io.IOException


@Singleton
class AuthenticationIAuthenticator @Inject constructor(private val api: APIServiceHolder) : Authenticator {



    @SuppressLint("CheckResult")
    override fun authenticate(route: Route, response: Response): Request? {
        if (api== null) {
            //we cannot answer the challenge as no token service is available

            return null //as per contract of Retrofit Authenticator interface for when unable to contest a challenge
        }
        Log.e("___aaaaa", response.code().toString())
      val token=  api.apiService()!!.getNewAccessToken(AuthBodyModel("09367760615","M.Razi")).execute().body()!!.token
Hawk.put("token",token)
         return response.request().newBuilder()
         .header("Authorization","Bearer $token")
             .build()
    }

}