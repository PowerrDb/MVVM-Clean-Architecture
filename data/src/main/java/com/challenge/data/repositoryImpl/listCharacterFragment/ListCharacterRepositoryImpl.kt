package com.challenge.data.repositoryImpl.listCharacterFragment


import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.data.datasource.listCharacterFragment.pagingByPage.ListOfCharacterDataSourceFactory
import com.challenge.data.datasource.listCharacterFragment.pagingByPage.PageKeyedListOfCharacterDataSource.Companion.NETWORK_PAGE_SIZE
import com.challenge.data.repositoryImpl.BaseRepositoryImpl
import com.challenge.domain.entity.Entity
import com.challenge.domain.repository.listCharacterFragment.ListCharacterRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


class ListCharacterRepositoryImpl(
    private val apiSource: ListCharacterApiDataSource

) : BaseRepositoryImpl<Entity.Character>(),
    ListCharacterRepository {


    override fun getCharacterInfo(id: String): Flowable<Entity.Character> {
        return apiSource.getCharacterInfo(id)
    }


    override fun getListOfcharacters(filter : String): Flowable<PagedList<Entity.Character>> {
        val characterDataSourceFactory = ListOfCharacterDataSourceFactory(apiSource,filter)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(NETWORK_PAGE_SIZE)
            .build()
        return RxPagedListBuilder(characterDataSourceFactory, config).buildFlowable(
            BackpressureStrategy.BUFFER
        )
    }
}

