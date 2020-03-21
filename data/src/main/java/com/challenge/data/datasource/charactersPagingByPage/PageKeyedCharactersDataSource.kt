package com.challenge.data.datasource.charactersPagingByPage

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.challenge.data.datasource.ListCharacterApiDataSource
import com.challenge.domain.entity.Entity

class PageKeyedCharactersDataSource(
    private val listApi: ListCharacterApiDataSource,
    private val filter: String
) : PageKeyedDataSource<Int, Entity.Character>() {
    @SuppressLint("CheckResult")
    var currentPage=0
    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Entity.Character>
    ) {
          currentPage = 1
          nextPage = currentPage + 1



        listApi.getListOfCharacters(currentPage,
            NETWORK_PAGE_SIZE,filter)
            .subscribe({
                callback.onResult(it, null, nextPage)
                currentPage=nextPage

            },{
                Log.e("getListCharacters",it.message.toString())
            })





       /* getListCharacters(listApi,currentPage, NETWORK_PAGE_SIZE,filter) {data->
              when (data) {
                  is ResultState.Success ->{
                      Log.e("___PageSucc",data.data.size.toString())
                      val items = data?.data ?: emptyList()
                      callback.onResult(items, null, nextPage)}
                  is ResultState.Error->{
                      Log.e("__SDA",data.message.toString())
                  }
                  is ResultState.Loading->{
                      Log.e("__SDA","Loading")
                  }
              }
          }*/


    }

    var nextPage = currentPage + 1
    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Entity.Character>
    ) {

        listApi.getListOfCharacters(currentPage,
            NETWORK_PAGE_SIZE,filter).doOnRequest {
            Log.e("__SDasdA","Loading")
        }.subscribe({
            currentPage++
            callback.onResult(it, nextPage)
            Log.e("___PageSasducc",it.toString())
        },{
            Log.e("__SDasdA","errrrrrr")
        })


      /*  getListCharacters(listApi,currentPage, NETWORK_PAGE_SIZE) {data->
            when (data) {
                is ResultState.Success ->{ callback.onResult(data.data,nextPage)}
            }

        }*/
       /* listApi.getListCharacters(currentPage, NETWORK_PAGE_SIZE)
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

