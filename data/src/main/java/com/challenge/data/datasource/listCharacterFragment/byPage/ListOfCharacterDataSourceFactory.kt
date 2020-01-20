package com.challenge.data.datasource.listCharacterFragment.byPage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.domain.entity.Entity


class ListOfCharacterDataSourceFactory (private val api: ListCharacterApiDataSource):  DataSource.Factory<Int , Entity.Character>() {
      val sourceLiveData = MutableLiveData<PageKeyedListOfCharacterDataSource>()
    override fun create(): DataSource<Int, Entity.Character> {
        val source = PageKeyedListOfCharacterDataSource(api)
        sourceLiveData.postValue(source)
        return source
    }
}