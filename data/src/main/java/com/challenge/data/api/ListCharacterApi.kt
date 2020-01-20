package com.challenge.data.api

import com.challenge.domain.models.AuthBodyModel
import com.challenge.domain.models.AuthResponseModel
import com.challenge.domain.models.ListCharacterModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*


interface ListCharacterApi {
    @GET("v1/character/list")
    fun getListOfCharacters(
        @Query("page") page: Int,
        @Query("perPage") pageSize: Int
    ): Flowable<List<ListCharacterModel>>

    @GET("v1/Character/{id}/info")
    fun getCharacterInfo(@Path("id") id: String): Flowable<ListCharacterModel>


    @POST("v1/users/authenticate")
    fun getNewAccessToken(
        @Body auth: AuthBodyModel = AuthBodyModel(
            "09367760615",
            "M.Razi"
        )
    ): Call<AuthResponseModel>

}