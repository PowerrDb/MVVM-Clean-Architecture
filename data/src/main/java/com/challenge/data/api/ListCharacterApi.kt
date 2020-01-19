package com.challenge.data.api

import com.challenge.domain.models.ListCharacterModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface ListCharacterApi {
    @GET("list")
    fun getListOfCharacters(@Query("page") page: Int,
                            @Query("perPage") pageSize: Int): Flowable<List<ListCharacterModel>>
}