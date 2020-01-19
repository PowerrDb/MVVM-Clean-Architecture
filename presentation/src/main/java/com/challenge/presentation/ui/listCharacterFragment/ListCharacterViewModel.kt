package com.challenge.presentation.ui.listCharacterFragment

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.challenge.presentation.ui.base.BaseViewModel
import com.challenge.domain.usecase.listCharacterFragment.GetListCharacterUseCase
import com.challenge.domain.entity.Entity
import com.challenge.presentation.common.OperationLiveData
import com.challenge.domain.common.ResultState
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ListCharacterViewModel @Inject constructor(private val useCase: GetListCharacterUseCase) :
    BaseViewModel(), LifecycleObserver {

    private var tempDispossable: Disposable? = null

    private val fetch = MutableLiveData<String>()

    fun fetchData() {
        fetch.postValue("")
    }

    val charactersLiveData: LiveData<ResultState<PagedList<Entity.Character>>> =
        Transformations.switchMap(fetch) {
            Log.e("___",it.toString())
            OperationLiveData<ResultState<PagedList<Entity.Character>>> {
                if (tempDispossable?.isDisposed != true)
                    tempDispossable?.dispose()
                tempDispossable = useCase.getListOfCharacters().subscribe { resultState ->

                    postValue((resultState))
                }
                tempDispossable?.track()
            }
        }

    fun getCharacters() {
        fetch.postValue("")
    }


}