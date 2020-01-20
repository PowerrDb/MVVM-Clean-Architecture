package com.challenge.data.repositoryImpl.listCharacterFragment


import android.util.Log
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.data.datasource.listCharacterFragment.byPage.ListOfCharacterDataSourceFactory
import com.challenge.data.datasource.listCharacterFragment.byPage.PageKeyedListOfCharacterDataSource.Companion.NETWORK_PAGE_SIZE
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


    override fun getListOfcharacters(): Flowable<ResultState<PagedList<Entity.Character>>> {
        val characterDataSourceFactory = ListOfCharacterDataSourceFactory(apiSource)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(NETWORK_PAGE_SIZE)
            .build()


           val data = RxPagedListBuilder(characterDataSourceFactory,config).buildObservable()

            return data

                .map { data ->
                    Log.e("data__",data.size.toString())
                    if (data.isNotEmpty())
                        ResultState.Success(data) as ResultState<PagedList<Entity.Character>>
                    else
                        ResultState.Loading(data) as ResultState<PagedList<Entity.Character>>
                }
                .onErrorReturn { e -> ResultState.Error(e, null) }.toFlowable(BackpressureStrategy.BUFFER)



            }
    }

