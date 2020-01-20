package com.challenge.domain.entity

sealed class Entity {

    data class Character(
        val id: Int,
        val name: String,
        val picture: String,
        val type: String,
        val score: Double
    ) : Entity()


}