package com.aystudio.watchlist.utils

import kotlinx.serialization.Serializable


sealed class Routes {

    @Serializable
    data object MOVIES : Routes()

    @Serializable
    data object SEARCH : Routes()

    @Serializable
    data object FAVOURITES : Routes()

    @Serializable
    data class DETAILS(val result: String) : Routes()

    @Serializable
    data class ViewMore(val category: String, val heading: String): Routes()
}