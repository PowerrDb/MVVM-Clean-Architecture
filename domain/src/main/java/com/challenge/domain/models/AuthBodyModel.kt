package com.challenge.domain.models

import com.google.gson.annotations.SerializedName
import com.sun.xml.internal.ws.developer.Serialization

data class AuthBodyModel(
    @SerializedName("password")  val password: String,
    @SerializedName("username") val username: String
)