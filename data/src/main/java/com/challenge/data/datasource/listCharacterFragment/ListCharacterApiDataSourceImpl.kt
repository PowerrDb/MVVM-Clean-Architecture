package com.challenge.data.datasource.listCharacterFragment

import com.challenge.data.api.ListCharacterApi
import com.challenge.data.extention.applyIoScheduler
import com.challenge.data.mapper.map
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.domain.models.ListCharacterModel
import io.reactivex.Flowable


class ListCharacterApiDataSourceImpl(private val api: ListCharacterApi) :
    ListCharacterApiDataSource {
    override fun getCharacterInfo(id: String): Flowable<Entity.Character> {
        return api.getCharacterInfo(id)
            .applyIoScheduler()
            .map { info->info.map() }
    }

    override fun getListOfCharacters(page: Int, pageSize: Int): Flowable<List<Entity.Character>> {
        return api.getListOfCharacters(page,pageSize)
            .map { item -> item.map { it.map() } }
    }




}