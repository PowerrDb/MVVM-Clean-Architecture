package com.challenge.data.repositoryImpl.listCharacterFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.data.datasource.listCharacterFragment.getListOfCharacters
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity

class RepoBoundaryCallback(
    private val apiSource: ListCharacterApiDataSource
) : PagedList.BoundaryCallback<Entity.Character>() {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1
    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should send request to the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to send a request to the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Entity.Character) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getListOfCharacters(
            apiSource,
            lastRequestedPage,
            NETWORK_PAGE_SIZE
        ) { characters ->
            when (characters) {
                is ResultState.Success -> {

                }
                is ResultState.Error -> {
                    _networkErrors.postValue(characters.throwable.message)
                    isRequestInProgress = false
                }
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}