package com.aystudio.watchlist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aystudio.watchlist.dsa.binarySearch
import com.aystudio.watchlist.dsa.greedySortByVoteAverage
import com.aystudio.watchlist.dsa.quickSort
import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import com.aystudio.watchlist.presentation.models.toMoviesModelClass

@Composable
fun CardDisplayDatabaseComponent(
    modifier: Modifier,
    list: List<DatabaseModelClass>,
    navController: NavController,
    query: String = "",
    action: String = ""
) {

       val dsaList = when (action) {
            "BinarySearch" -> {
                val sortedList = list.sortedBy { it.title }
                val index = if (query.isNotBlank()) binarySearch(sortedList, query, 0, sortedList.size - 1)
                else -1

                if (index != -1) listOf(sortedList[index]) else sortedList
            }

            "QuickSort" -> {
                val sortedList = list.toMutableStateList()
                quickSort(sortedList, 0, sortedList.size - 1)
                sortedList
            }

           "Greedy" -> {
               val mutableList = list.toMutableList()
               greedySortByVoteAverage(mutableList, 40.0)
           }

            else -> {
                list
            }

        }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(160.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(dsaList) { item ->
            val modelClass = item.toMoviesModelClass()
            CardComponent(
                data = modelClass,
                isLoading = false,
                navController = navController,
                isSearchScreen = true
            )
        }
    }
}




