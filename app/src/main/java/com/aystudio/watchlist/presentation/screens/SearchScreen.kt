package com.aystudio.watchlist.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.aystudio.watchlist.presentation.components.CardStateComponent
import com.aystudio.watchlist.presentation.components.SearchBarComponent
import com.aystudio.watchlist.presentation.viewmodel.MoviesViewModel

@Composable
fun SearchScreen(modifier: Modifier, navController: NavController) {

    val viewModel: MoviesViewModel = hiltViewModel()


    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    val searchResult = viewModel.searchResult.collectAsLazyPagingItems()


    Column(modifier = modifier) {

        SearchBarComponent(query = searchQuery, onClear = { viewModel.onQueryChanged("") },
            onQueryChange = {
                viewModel.onQueryChanged(it)
            })


        CardStateComponent(
            category = searchResult,
            navController = navController,
            isSearchScreen = true
        )

    }

}