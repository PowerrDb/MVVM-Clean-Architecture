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

    private val fetchCharacterList = MutableLiveData<String>()

    private val fetchCharacterInfo = MutableLiveData<String>()

    val characterInfoLiveData: LiveData<ResultState<Entity.Character>> =
        Transformations.switchMap(fetchCharacterInfo) {
            Log.e("__InfoLiveData",it.toString())
            OperationLiveData<ResultState<Entity.Character>> {
                if (tempDispossable?.isDisposed != true)
                    tempDispossable?.dispose()
                tempDispossable = useCase.getCharacterInfo(it)
                    .doOnRequest { postValue(ResultState.Loading(Entity.Character())) }
                    .subscribe(
                    {data->
                        postValue(data)
                    },{
                        error->
                        postValue(ResultState.Error(error,null))
                    }
                )
                tempDispossable?.track()
            }
        }

    val charactersLiveData: LiveData<ResultState<PagedList<Entity.Character>>> =
        Transformations.switchMap(fetchCharacterList) {
            Log.e("__a_",it.toString())
            OperationLiveData<ResultState<PagedList<Entity.Character>>> {
                if (tempDispossable?.isDisposed != true)
                    tempDispossable?.dispose()
                tempDispossable = useCase.getListOfCharacters()
                    .subscribe(
                    {data->
                        postValue(data)
                    },
                    {
                        postValue(ResultState.Error(it,null))
                    }
                )
                tempDispossable?.track()
            }
        }

    fun getCharacters() {
        fetchCharacterList.postValue("")
    }


    fun getCharacterInfo(id : String) {
        fetchCharacterInfo.postValue(id)
    }


}