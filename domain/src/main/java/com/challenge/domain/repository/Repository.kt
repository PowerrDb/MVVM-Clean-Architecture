package com.challenge.domain.repository

import androidx.paging.PagedList
import com.challenge.domain.repository.BaseRepository
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import io.reactivex.Flowable
import io.reactivex.Observable


interface Repository : BaseRepository {
    fun getListCharacters(filter : String=""): Flowable<PagedList<Entity.Character>>

    fun getInfoCharacter(id: String): Flowable<Entity.Character>


}