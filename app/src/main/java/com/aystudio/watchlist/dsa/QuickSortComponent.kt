package com.aystudio.watchlist.dsa

import com.aystudio.watchlist.presentation.models.DatabaseModelClass

fun swap(list: MutableList<DatabaseModelClass>, i: Int, j: Int) {
    val temp = list[i]
    list[i] = list[j]
    list[j] = temp
}

fun partition(list: MutableList<DatabaseModelClass>, low: Int, high: Int): Int {
    val pivot = list[high].popularity ?: 0.0
    var i = low - 1

    for (j in low until high) {
        if ((list[j].popularity ?: 0.0) < pivot) {
            i++
            swap(list, i, j)
        }
    }

    swap(list, i + 1, high)
    return i + 1
}

fun quickSort(list: MutableList<DatabaseModelClass>, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(list, low, high)

        quickSort(list, low, pi - 1)
        quickSort(list, pi + 1, high)
    }
}

