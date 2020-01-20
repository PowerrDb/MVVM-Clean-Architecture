package com.challenge.domain.repository.listCharacterFragment

import androidx.paging.PagedList
import com.challenge.domain.repository.BaseRepository
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import io.reactivex.Flowable


interface ListCharacterRepository : BaseRepository {
    fun getListOfcharacters(): Flowable<ResultState<PagedList<Entity.Character>>>

    fun getCharacterInfo(id : String): Flowable<ResultState<Entity.Character>>

}