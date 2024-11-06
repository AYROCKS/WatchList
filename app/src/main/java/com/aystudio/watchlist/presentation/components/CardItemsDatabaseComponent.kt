package com.aystudio.watchlist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import com.aystudio.watchlist.presentation.models.toMoviesModelClass
import com.aystudio.watchlist.utils.Constants

@Composable
fun CardDisplayDatabaseComponent(
    modifier: Modifier,
    list: List<DatabaseModelClass>,
    navController: NavController
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(160.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        items(list) {
            it.let {
                val modelClass = it.toMoviesModelClass()
                CardComponent(data = modelClass, isLoading = false, navController = navController)

            }
        }
    }
}


