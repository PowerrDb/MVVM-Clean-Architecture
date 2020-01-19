package com.challenge.data.datasource.listCharacterFragment

import com.challenge.data.api.ListCharacterApi
import com.challenge.data.extention.applyIoScheduler
import com.challenge.domain.models.ListCharacterModel
import io.reactivex.Flowable


class ListCharacterApiDataSourceImpl(private val api: ListCharacterApi) :
    ListCharacterApiDataSource {
    override fun getListOfCharacters(page: Int, pageSize: Int): Flowable<List<ListCharacterModel>> {
        return api.getListOfCharacters(page,pageSize)
            .applyIoScheduler()
            .map { item -> item.map { it } }
    }




}