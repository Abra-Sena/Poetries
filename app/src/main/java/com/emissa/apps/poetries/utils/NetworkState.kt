package com.emissa.apps.poetries.utils

sealed interface NetworkState {
    object LOADING: NetworkState
    class SUCCESS<T>(val response: T) : NetworkState
    class ERROR(val error: Throwable) : NetworkState
}