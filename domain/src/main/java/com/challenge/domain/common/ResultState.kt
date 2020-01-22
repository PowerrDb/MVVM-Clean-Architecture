package com.challenge.domain.common

sealed class ResultState<T> {

    class Loading<T> : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val message: Throwable) : ResultState<T>()

    companion object {
        fun <T> loading(): ResultState<T> = Loading()
        fun <T> success(data: T): ResultState<T> = Success(data)
        fun <T> error(message: Throwable): ResultState<T> = Error(message)
    }

}