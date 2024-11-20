package com.aystudio.watchlist.presentation.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aystudio.watchlist.presentation.components.DetailsScreenComponentsComponent
import com.aystudio.watchlist.presentation.models.Result
import com.aystudio.watchlist.presentation.viewmodel.MoviesViewModel
import kotlinx.serialization.json.Json

@Composable
fun DetailsScreen(
    modifier: Modifier,
    result: String,
    viewModel: MoviesViewModel = hiltViewModel()
) {

    val resultModel = Json.decodeFromString<Result>(result)

    viewModel.getMovieVideo(resultModel.id)
    viewModel.getMovieCast(resultModel.id)

    val genres by viewModel.movieGenre.collectAsStateWithLifecycle()
    val movieVideo by viewModel.movieVideo.collectAsStateWithLifecycle()
    val movieCast by viewModel.movieCast.collectAsStateWithLifecycle()


    val genreMap = genres.genres.associateBy { it.id }
    val genreNames = resultModel.genre_ids.mapNotNull { id -> genreMap[id]?.name }




    DetailsScreenComponentsComponent(
        modifier = modifier,
        modelClass = resultModel,
        genreName = genreNames,
        videoModelClass = movieVideo,
        movieCast = movieCast
    )

}