package com.challenge.presentation.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.domain.usecase.GetUseCase
import com.challenge.presentation.common.OperationLiveData
import com.challenge.presentation.ui.base.BaseViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private val useCase: GetUseCase

) : BaseViewModel(), LifecycleObserver {

    private var tempDisposable: Disposable? = null

    private val fetchInfoCharacter = MutableLiveData<String>()
    private val fetchCharacterList = MutableLiveData<String>()

    init {
     getCharacters()
    }
    fun getCharacters(search: String = "") {
        fetchCharacterList.postValue(search)
    }

    fun getCharacterInfo(id: String) {
        fetchInfoCharacter.postValue(id)
    }

    val infoCharacterLiveData: LiveData<ResultState<Entity.Character>> =
        Transformations.switchMap(fetchInfoCharacter) { searchValue ->
            OperationLiveData<ResultState<Entity.Character>> {
                if (tempDisposable?.isDisposed != true)
                    tempDisposable?.dispose()
                tempDisposable = useCase.getInfoCharacter(searchValue)
                    .doOnRequest { postValue(ResultState.Loading()) }
                    .doOnError { postValue(ResultState.Error(it)) }
                    .subscribe({
                        postValue(ResultState.Success(it))
                    }, {
                        postValue(ResultState.Error(it))
                    })
                tempDisposable?.track()
            }
        }

    val charactersLiveData: LiveData<ResultState<PagedList<Entity.Character>>> =
        Transformations.switchMap(fetchCharacterList) {
            OperationLiveData<ResultState<PagedList<Entity.Character>>> {
                if (tempDisposable?.isDisposed != true)
                    tempDisposable?.dispose()
                tempDisposable = useCase.getListCharacters(it)
                    .doOnRequest { postValue(ResultState.loading()) }
                    .doOnError { postValue(ResultState.error(it)) }
                    .subscribe(
                        { data -> postValue(ResultState.Success(data)) },
                        { postValue(ResultState.error(it)) })
                tempDisposable?.track()

            }
        }



}