package com.aystudio.watchlist.dsa

import com.aystudio.watchlist.presentation.models.DatabaseModelClass

fun binarySearch(
    list: List<DatabaseModelClass>,
    query: String,
    low: Int,
    high: Int
): Int {


    if (high >= low) {
        val mid = low + (high - low) / 2
        val midValue = list[mid].title?.lowercase() ?: ""

        return if (midValue.startsWith(query.lowercase())) mid
        else if (midValue < query.lowercase())
            binarySearch(list, query, mid + 1, high)
        else
            binarySearch(list, query, low, mid - 1)

    } else
        return -1

}




