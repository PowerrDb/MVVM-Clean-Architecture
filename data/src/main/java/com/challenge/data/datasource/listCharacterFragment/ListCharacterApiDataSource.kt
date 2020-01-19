package com.challenge.data.datasource.listCharacterFragment

import android.annotation.SuppressLint
import android.util.Log
import com.challenge.data.datasource.BaseDataSource
import com.challenge.domain.common.ResultState
import com.challenge.domain.models.ListCharacterModel
import io.reactivex.Flowable


interface ListCharacterApiDataSource : BaseDataSource {

    fun getListOfCharacters(page: Int, pageSize: Int): Flowable<List<ListCharacterModel>>
}

@SuppressLint("CheckResult")
fun getListOfCharacters(
    apiSource: ListCharacterApiDataSource,
    page: Int,
    itemsPerPage: Int,
    onResult: (result: ResultState<List<ListCharacterModel>>) -> Unit
) {

    apiSource.getListOfCharacters(page, itemsPerPage)
        .subscribe({ data ->
            onResult(ResultState.Success(data))
            if (data.isNotEmpty())
                Log.e("___apiDataSou",data[0].name)
        }, { throwable ->
            onResult(ResultState.Error(throwable, null))
        })
}