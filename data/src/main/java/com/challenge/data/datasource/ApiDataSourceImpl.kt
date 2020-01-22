package com.challenge.data.datasource

import com.challenge.data.api.MApi
import com.challenge.data.extention.applyIoScheduler
import com.challenge.data.mapper.map
import com.challenge.domain.entity.Entity
import io.reactivex.Flowable


class ApiDataSourceImpl(private val api: MApi) :
    ListCharacterApiDataSource {
    override fun getCharacterInfo(id: String): Flowable<Entity.Character> {
        return api.getCharacterInfo(id)
            .applyIoScheduler()
            .map { info->info.map() }
    }

    override fun getListOfCharacters(page: Int, pageSize: Int,filter : String): Flowable<List<Entity.Character>> {
        return api.getListOfCharacters(page,pageSize,filter)
            .map { item -> item.map { it.map() } }
    }




}