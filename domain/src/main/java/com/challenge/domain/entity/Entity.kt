package com.challenge.domain.entity

sealed class Entity {

    data class Character(
        val id: String,
        val name: String,
        val picture: String,
        val type: String,
        val score: Int
    ) : Entity()


}