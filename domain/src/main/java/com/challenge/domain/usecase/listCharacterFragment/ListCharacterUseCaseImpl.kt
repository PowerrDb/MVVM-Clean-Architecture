package com.challenge.domain.usecase.listCharacterFragment

import androidx.paging.PagedList
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.domain.repository.listCharacterFragment.ListCharacterRepository
import io.reactivex.Flowable


class ListCharacterUseCaseImpl(
    private val repository: ListCharacterRepository
) : GetListCharacterUseCase {

    override fun getCharacterInfo(id : String): Flowable<ResultState<Entity.Character>> =
        repository.getCharacterInfo(id)


    override fun getListOfCharacters(): Flowable<ResultState<PagedList<Entity.Character>>> =
        repository.getListOfcharacters()

}