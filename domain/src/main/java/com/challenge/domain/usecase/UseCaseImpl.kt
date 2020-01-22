package com.challenge.domain.usecase

import androidx.paging.PagedList
import com.challenge.domain.entity.Entity
import com.challenge.domain.repository.Repository
import io.reactivex.Flowable


class UseCaseImpl(
    private val repository: Repository
) : GetUseCase {

    override fun getInfoCharacter(id : String): Flowable<Entity.Character> =
        repository.getInfoCharacter(id)


    override fun getListCharacters(filter : String): Flowable<PagedList<Entity.Character>> =
        repository.getListCharacters(filter)

}