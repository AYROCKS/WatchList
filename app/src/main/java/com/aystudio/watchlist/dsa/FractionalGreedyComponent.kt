package com.aystudio.watchlist.dsa

import com.aystudio.watchlist.presentation.models.DatabaseModelClass

fun greedySortByVoteAverage(list: MutableList<DatabaseModelClass>, maxWeight: Double): List<DatabaseModelClass> {
    var currentWeight = maxWeight
    var totalValue = 0.0
    val selectedItems = mutableListOf<DatabaseModelClass>()


    val sortedList = list.sortedByDescending { it.vote_average ?: 0.0 }

    for (item in sortedList) {
        val itemWeight = item.vote_average ?: 0.0
        val itemValue = item.vote_average ?: 0.0

        if (currentWeight >= itemWeight) {
            currentWeight -= itemWeight
            totalValue += itemValue
            selectedItems.add(item)
        } else if (currentWeight > 0) {

            val fraction = currentWeight / itemWeight
            totalValue += itemValue * fraction
            selectedItems.add(item)
            break
        }

        if (currentWeight == 0.0) {
            break
        }
    }


    return selectedItems
}
