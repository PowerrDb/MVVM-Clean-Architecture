package com.challenge.domain.repository.listCharacterFragment

import androidx.paging.PagedList
import com.challenge.domain.repository.BaseRepository
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import io.reactivex.Flowable
import io.reactivex.Observable


interface ListCharacterRepository : BaseRepository {
    fun getListOfcharacters(filter : String=""): Flowable<PagedList<Entity.Character>>

    fun getCharacterInfo(id: String): Flowable<Entity.Character>


}