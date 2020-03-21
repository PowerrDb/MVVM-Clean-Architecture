package com.challenge.data.datasource.charactersPagingByPage

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.challenge.data.datasource.ListCharacterApiDataSource
import com.challenge.domain.entity.Entity


class CharactersDataSourceFactory (private val api: ListCharacterApiDataSource, private val search: String):  DataSource.Factory<Int , Entity.Character>() {
      private val sourceLiveData = MutableLiveData<PageKeyedCharactersDataSource>()
    override fun create(): DataSource<Int, Entity.Character> {
        val source =
            PageKeyedCharactersDataSource(api, search)
        sourceLiveData.postValue(source)
        return source
    }
}