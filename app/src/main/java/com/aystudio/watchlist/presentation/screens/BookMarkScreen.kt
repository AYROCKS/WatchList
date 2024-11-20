package com.aystudio.watchlist.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aystudio.watchlist.dsa.DropDownListComponent
import com.aystudio.watchlist.presentation.components.CardDisplayDatabaseComponent
import com.aystudio.watchlist.presentation.components.SearchBarComponent
import com.aystudio.watchlist.presentation.viewmodel.MoviesDatabaseViewModel

@Composable
fun BookMarkScreen(
    modifier: Modifier,
    viewModel: MoviesDatabaseViewModel = hiltViewModel(),
    navController: NavController
) {
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()
    val moviesList = viewModel.movies.collectAsStateWithLifecycle().value

    var dsaAction by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBarComponent(
                query = query,
                onQueryChange = {
                    viewModel.onQueryChanged(it)
                    dsaAction = "BinarySearch"
                },
                onClear = { viewModel.onQueryChanged("") },
                modifier = Modifier.weight(1f)
            )

            DropDownListComponent {
                dsaAction = it
            }

        }

        CardDisplayDatabaseComponent(
            modifier = Modifier,
            list = moviesList,
            navController = navController,
            query = query,
            action = dsaAction
        )
    }
}


