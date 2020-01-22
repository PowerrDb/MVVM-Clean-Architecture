package com.challenge.domain.usecase.listCharacterFragment

import androidx.paging.PagedList
import com.challenge.domain.common.ResultState
import com.challenge.domain.usecase.BaseUseCase
import com.challenge.domain.entity.Entity
import io.reactivex.Flowable


interface GetListCharacterUseCase : BaseUseCase {
    fun getListOfCharacters(filter : String=""): Flowable<PagedList<Entity.Character>>

    fun getCharacterInfo(id : String): Flowable<Entity.Character>

}