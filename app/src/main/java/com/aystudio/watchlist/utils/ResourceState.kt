package com.aystudio.watchlist.utils

sealed class ResourceState<out T> {

    data class SUCCESS<out T>(val data: T) : ResourceState<T>()
    data class ERROR(val message: String) : ResourceState<Nothing>()
    data object LOADING: ResourceState<Nothing>()
}
