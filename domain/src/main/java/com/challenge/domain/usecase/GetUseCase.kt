package com.challenge.domain.usecase

import androidx.paging.PagedList
import com.challenge.domain.entity.Entity
import io.reactivex.Flowable


interface GetUseCase : BaseUseCase {
    fun getListCharacters(filter: String = ""): Flowable<PagedList<Entity.Character>>
    fun getInfoCharacter(id: String): Flowable<Entity.Character>

}