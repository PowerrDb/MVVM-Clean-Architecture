package com.challenge.domain.entity

sealed class Entity {

    data class Character(
        val id: Int = 0,
        val name: String="",
        val picture: String="",
        val type: String="",
        val score: Double=0.0
    ) : Entity()


}