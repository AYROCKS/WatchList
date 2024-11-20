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
            println("Added object ${item.title} (Vote Average: $itemValue) completely in the bag. Space left: $currentWeight")
        } else if (currentWeight > 0) {

            val fraction = currentWeight / itemWeight
            totalValue += itemValue * fraction
            selectedItems.add(item)
            println("Added ${(fraction * 100).toInt()}% of object ${item.title} (Vote Average: $itemValue) in the bag.")
            break // Since we're done after taking the fraction of the last item
        }

        // If the knapsack is full, stop the process
        if (currentWeight == 0.0) {
            break
        }
    }

    // Print the final value of the selected items
    println("Filled the bag with objects worth $totalValue.")
    return selectedItems
}
