package com.challenge.data.api

import com.challenge.domain.models.AuthBodyModel
import com.challenge.domain.models.AuthResponseModel
import com.challenge.domain.models.ListCharacterModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthApi {
    @POST("users/authenticate")
    fun getNewAccessToken(@Body auth : AuthBodyModel = AuthBodyModel("09367760615","M.Razi")): Call<AuthResponseModel>
}