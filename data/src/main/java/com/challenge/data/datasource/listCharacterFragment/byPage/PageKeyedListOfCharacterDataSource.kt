package com.challenge.data.datasource.listCharacterFragment.byPage

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.P
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.challenge.data.api.ListCharacterApi
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.data.datasource.listCharacterFragment.getListOfCharacters
import com.challenge.data.extention.applyIoScheduler
import com.challenge.data.mapper.map
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageKeyedListOfCharacterDataSource(
    private val listApi: ListCharacterApiDataSource
) : PageKeyedDataSource<Int, Entity.Character>() {
    @SuppressLint("CheckResult")

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Entity.Character>
    ) {
        val currentPage = 1
        val nextPage = currentPage + 1



        getListOfCharacters(listApi,currentPage, NETWORK_PAGE_SIZE) {data->
            when (data) {
                is ResultState.Success ->{
                    Log.e("___Pagekeued",data.data.size.toString())
                    val items = data?.data ?: emptyList()
                    callback.onResult(items,null,nextPage)}
            }

        }


    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Entity.Character>
    ) {
        val currentPage = 1
        val nextPage = currentPage + 1


        getListOfCharacters(listApi,currentPage, NETWORK_PAGE_SIZE) {data->
            when (data) {
                is ResultState.Success ->{ callback.onResult(data.data,nextPage)}
            }

        }
       /* listApi.getListOfCharacters(currentPage, NETWORK_PAGE_SIZE)
            .applyIoScheduler()
            .doOnError{
                Log.e("___v",it.toString())
            }
            .subscribe({ callback.onResult(it,nextPage)},{})*/
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Entity.Character>
    ) {

    }

    companion object {
         const val NETWORK_PAGE_SIZE = 10
    }
}

