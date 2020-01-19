package com.challenge.data.datasource.listCharacterFragment.byPage

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.domain.entity.Entity


class ListOfCharacterDataSourceFactory (private val api: ListCharacterApiDataSource):  DataSource.Factory<String , Entity.Character>() {
     private val sourceLiveData = MutableLiveData<PageKeyedListOfCharacterDataSource>()
    override fun create(): DataSource<String, Entity.Character> {
        val source = PageKeyedListOfCharacterDataSource(api)
        sourceLiveData.postValue(source)
        return source
    }
}