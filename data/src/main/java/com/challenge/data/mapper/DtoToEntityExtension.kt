package com.challenge.data.mapper

import com.challenge.domain.entity.Entity
import com.challenge.domain.models.ListCharacterModel


fun ListCharacterModel.map() = Entity.Character(
    name = name,
    id = id,
    type = type,
    picture = picture,
    score = score
)

