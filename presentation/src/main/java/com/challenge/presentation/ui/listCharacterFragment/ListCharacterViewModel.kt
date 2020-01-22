package com.challenge.presentation.ui.listCharacterFragment

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.domain.usecase.listCharacterFragment.GetListCharacterUseCase
import com.challenge.presentation.common.OperationLiveData
import com.challenge.presentation.ui.base.BaseViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ListCharacterViewModel @Inject constructor(
    private val useCase: GetListCharacterUseCase

) : BaseViewModel(), LifecycleObserver {


    private var tempDispossable: Disposable? = null


    private val fetchCharacterInfo = MutableLiveData<String>()
    val InfoCharacterLiveData: LiveData<ResultState<Entity.Character>> =
        Transformations.switchMap(fetchCharacterInfo) { searchValue ->
            Log.e("__InfoLiveData", searchValue.toString())
            OperationLiveData<ResultState<Entity.Character>> {
                if (tempDispossable?.isDisposed != true)
                    tempDispossable?.dispose()
                tempDispossable = useCase.getCharacterInfo(searchValue)
                    .doOnRequest { postValue(ResultState.Loading()) }
                    .doOnError { postValue(ResultState.Error(it)) }
                    .subscribe(
                        { data ->
                            postValue(ResultState.Success(data))
                        }, { error ->
                            postValue(ResultState.Error(error))
                        })
                tempDispossable?.track()
            }
        }


    private val fetchCharacterList = MutableLiveData<String>()

    val charactersLiveData: LiveData<ResultState<PagedList<Entity.Character>>> =
        Transformations.switchMap(fetchCharacterList) {

            OperationLiveData<ResultState<PagedList<Entity.Character>>> {
                if (tempDispossable?.isDisposed != true)
                    tempDispossable?.dispose()
                tempDispossable = useCase.getListOfCharacters(it)
                    .doOnRequest { postValue(ResultState.loading()) }
                    .doOnError { postValue(ResultState.error(it)) }
                    .subscribe(
                        { data -> postValue(ResultState.Success(data)) },
                        { postValue(ResultState.error(it)) })
                tempDispossable?.track()
            }

        }


    fun getCharacters(search: String = "") {
        fetchCharacterList.postValue(search)
    }


    fun getCharacterInfo(id: String) {
        fetchCharacterInfo.postValue(id)
    }


}