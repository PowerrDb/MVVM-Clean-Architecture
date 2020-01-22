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

  /*  *//**
     * A state of [data] which shows that we know there is still an update to come.
     *//*
    data class Loading<T>(val data: T) : ResultState<T>()

    *//**
     * A state that shows the [data] is the last known update.
     *//*
    data class Success<T>(val data: T) : ResultState<T>()

    *//**
     * A state to show a [throwable] is thrown.
     *//*
    data class Error<T>(val throwable: Throwable, val data: T?) : ResultState<T>()*/
}