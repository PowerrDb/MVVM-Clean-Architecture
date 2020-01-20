package com.challenge.data.repositoryImpl.listCharacterFragment


import android.util.Log
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.data.datasource.listCharacterFragment.pagingByPage.ListOfCharacterDataSourceFactory
import com.challenge.data.datasource.listCharacterFragment.pagingByPage.PageKeyedListOfCharacterDataSource.Companion.NETWORK_PAGE_SIZE
import com.challenge.data.extention.applyIoScheduler
import com.challenge.data.repositoryImpl.BaseRepositoryImpl
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.domain.repository.listCharacterFragment.ListCharacterRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


class ListCharacterRepositoryImpl(
    private val apiSource: ListCharacterApiDataSource

) : BaseRepositoryImpl<Entity.Character>(),
    ListCharacterRepository {
    override fun getCharacterInfo(id: String): Flowable<ResultState<Entity.Character>> {
        return apiSource.getCharacterInfo(id)
            .doOnRequest { ResultState.Loading(Entity.Character()) }
            .map {
                ResultState.Success(it)
            }






    }


    override fun getListOfcharacters(): Flowable<ResultState<PagedList<Entity.Character>>> {
        val characterDataSourceFactory = ListOfCharacterDataSourceFactory(apiSource)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(NETWORK_PAGE_SIZE)
            .build()


           val data = RxPagedListBuilder(characterDataSourceFactory,config).buildFlowable(BackpressureStrategy.BUFFER)

        return data
            .doOnRequest {
                Log.e("__iinja","asda")
                ResultState.Loading("") }
            .map {
                Log.e("__iinja",it.toString())
                ResultState.Success(it) }




            }
    }

