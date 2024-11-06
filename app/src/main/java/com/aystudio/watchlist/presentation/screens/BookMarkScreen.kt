package com.aystudio.watchlist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aystudio.watchlist.presentation.components.CardDisplayDatabaseComponent
import com.aystudio.watchlist.presentation.viewmodel.MoviesDatabaseViewModel

@Composable
fun BookMarkScreen(modifier: Modifier, viewModel: MoviesDatabaseViewModel = hiltViewModel(), navController: NavController){

    val moviesList = viewModel.movies.collectAsStateWithLifecycle().value

    CardDisplayDatabaseComponent(modifier = modifier,list = moviesList, navController = navController)
}