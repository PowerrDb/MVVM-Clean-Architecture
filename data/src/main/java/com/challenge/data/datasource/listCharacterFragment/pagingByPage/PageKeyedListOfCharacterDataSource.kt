package com.challenge.data.datasource.listCharacterFragment.pagingByPage

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity

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




        listApi.getListOfCharacters(currentPage, NETWORK_PAGE_SIZE).doOnRequest {
            Log.e("__SDA","Loading")
        }.subscribe({
            callback.onResult(it, null, nextPage)
            Log.e("___PageSucc",it.toString())
        },{
            Log.e("__SDA","errrrrrr")
        })

      /*  getListOfCharacters(listApi,currentPage, NETWORK_PAGE_SIZE) {data->
            when (data) {
                is ResultState.Success ->{

                    Log.e("___PageSucc",data.data.size.toString())
                    val items = data?.data ?: emptyList()
                    callback.onResult(items, null, nextPage)}
                is ResultState.Error->{
                    Log.e("__SDA","errrrrrr")
                }
                is ResultState.Loading->{
                    Log.e("__SDA","Loading")
                }
            }


        }*/


    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Entity.Character>
    ) {
        val currentPage = 1
        val nextPage = currentPage + 1
        listApi.getListOfCharacters(currentPage, NETWORK_PAGE_SIZE).doOnRequest {
            Log.e("__SDasdA","Loading")
        }.subscribe({
            callback.onResult(it, nextPage)
            Log.e("___PageSasducc",it.toString())
        },{
            Log.e("__SDasdA","errrrrrr")
        })


      /*  getListOfCharacters(listApi,currentPage, NETWORK_PAGE_SIZE) {data->
            when (data) {
                is ResultState.Success ->{ callback.onResult(data.data,nextPage)}
            }

        }*/
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

