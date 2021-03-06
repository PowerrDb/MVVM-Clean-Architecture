package com.challenge.data.datasource

import android.annotation.SuppressLint
import android.util.Log
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import io.reactivex.Flowable


interface ListCharacterApiDataSource : BaseDataSource {

    fun getListOfCharacters(page: Int, pageSize: Int,search : String=""): Flowable<List<Entity.Character>>

    fun getCharacterInfo(id: String): Flowable<Entity.Character>

}


@SuppressLint("CheckResult")
fun getListOfCharacters(
    apiSource: ListCharacterApiDataSource,
    page: Int,
    itemsPerPage: Int,
    filter : String="",
    onResult: (result: ResultState<List<Entity.Character>>) -> Unit
) {

    apiSource.getListOfCharacters(page, itemsPerPage,filter)
        .doOnRequest { Log.e("__dooo","loaaaaaaaaaad")
            ResultState.loading<Any>()
        }
        .subscribe({ data ->
            onResult(ResultState.Success(data))
            if (data.isNotEmpty())
                Log.e("___apiDataSou",data[0].name)
        }, { throwable ->
            onResult(ResultState.Error(throwable))
        })

}
