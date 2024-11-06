package com.aystudio.watchlist.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.aystudio.watchlist.presentation.components.BigDisplayResourceState
import com.aystudio.watchlist.presentation.components.CardDisplayComponent
import com.aystudio.watchlist.presentation.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(navController: NavController, viewModel: MoviesViewModel = hiltViewModel()) {


    val inCinemas = viewModel.inCinemas.collectAsLazyPagingItems()
    val trendingMovies = viewModel.trendingMovies.collectAsLazyPagingItems()
    val releasingMovies = viewModel.upcomingMovies.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val topRatedMovies=  viewModel.topRatedMovies.collectAsLazyPagingItems()



    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {

        BigDisplayResourceState(movie = inCinemas, navController = navController)

        CardDisplayComponent(
            category = trendingMovies,
            heading = "Trending Movies",
            isTrending = true,
            navController = navController,
            categoryCode = "trending",
            onToggle = { newTimeWindow ->
                viewModel.setTimeWindow(newTimeWindow)
                trendingMovies.retry()
            }
        )

        CardDisplayComponent(category =releasingMovies, heading = "Releasing Movies", categoryCode = "upcoming", navController = navController)
        CardDisplayComponent(category = popularMovies, heading = "Popular Movies", categoryCode = "popular", navController = navController)
        CardDisplayComponent(category = topRatedMovies, heading = "Top-Rated Movies", categoryCode = "top_rated", navController = navController)

        Spacer(modifier = Modifier.size(100.dp))
    }
}
