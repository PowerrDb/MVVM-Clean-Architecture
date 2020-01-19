package com.challenge.data.datasource.listCharacterFragment.byPage

import androidx.paging.PageKeyedDataSource
import com.challenge.data.api.ListCharacterApi
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.domain.entity.Entity

class PageKeyedListOfCharacterDataSource(
    private val listApi: ListCharacterApiDataSource
) : PageKeyedDataSource<String, Entity.Character>() {
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Entity.Character>
    ) {

    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, Entity.Character>
    ) {

    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, Entity.Character>
    ) {

    }
}