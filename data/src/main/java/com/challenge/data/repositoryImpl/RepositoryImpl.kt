package com.challenge.data.repositoryImpl


import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.challenge.data.datasource.ListCharacterApiDataSource
import com.challenge.data.datasource.charactersPagingByPage.CharactersDataSourceFactory
import com.challenge.data.datasource.charactersPagingByPage.PageKeyedCharactersDataSource.Companion.NETWORK_PAGE_SIZE
import com.challenge.domain.entity.Entity
import com.challenge.domain.repository.Repository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


class RepositoryImpl(
    private val apiSource: ListCharacterApiDataSource

) : BaseRepositoryImpl<Entity.Character>(),
    Repository {


    override fun getInfoCharacter(id: String): Flowable<Entity.Character> {
        return apiSource.getCharacterInfo(id)
    }


    override fun getListCharacters(filter : String): Flowable<PagedList<Entity.Character>> {
        val characterDataSourceFactory =
            CharactersDataSourceFactory(
                apiSource,
                filter
            )
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(NETWORK_PAGE_SIZE)
            .build()
        return RxPagedListBuilder(characterDataSourceFactory, config).buildFlowable(
            BackpressureStrategy.BUFFER
        )
    }
}

