package com.challenge.domain.models

data class AuthResponseModel(
    val firstName: String,
    val id: Int,
    val lastName: String,
    val token: String,
    val username: String
)