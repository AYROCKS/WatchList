package com.aystudio.watchlist.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.aystudio.watchlist.R
import com.aystudio.watchlist.presentation.models.Result
import com.aystudio.watchlist.presentation.viewmodel.MoviesViewModel
import com.aystudio.watchlist.utils.Constants
import com.aystudio.watchlist.utils.Routes
import kotlinx.coroutines.delay

@Composable
fun BigDisplayResourceState(
    movie: LazyPagingItems<Result>,
    navController: NavController,
) {


    when (movie.loadState.refresh) {
        is LoadState.Error -> {
            val error = (movie.loadState.refresh as LoadState.Error).error

            Box(modifier = Modifier.fillMaxSize().systemBarsPadding(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    movie.retry()
                }) {
                    Text(text = "Retry")
                }
            }

        }

        is LoadState.Loading -> {
            BigDisplayComponent(data = null, isLoading = true, navController = navController)
        }

        is LoadState.NotLoading -> {
            val dataCount = movie.itemCount

            if (dataCount > 0) {
                var position by rememberSaveable {
                    mutableIntStateOf(0)
                }

                LaunchedEffect(key1 = position) {
                    delay(10000)
                    position = (position + 1) % dataCount
                }

                val movieData = movie[position]
                BigDisplayComponent(
                    data = movieData,
                    isLoading = false,
                    navController = navController
                )
            } else {
                Text("No data available")
            }
        }
    }
}


@Composable
fun BigDisplayComponent(data: Result?, isLoading: Boolean, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStartPercent = 10, bottomEndPercent = 10)),
        contentAlignment = Alignment.TopStart,
    ) {

        if (isLoading)
            Image(
                painter = ColorPainter(Color.Gray.copy(alpha = 0.35f)),
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
            )
        else
            AsyncImage(
                model = Constants.IMAGE_BASE_URL + data?.backdrop_path,
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black)
                    .alpha(0.45f),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.image)

            )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .systemBarsPadding()
        ) {
            HeadingComponent(navController)

            ImageTextComponent(data = data, isLoading = isLoading, navController = navController)
        }

    }
}

@Composable
fun HeadingComponent(navController: NavController) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "In Cinemas",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )

        IconButton(onClick = {
            navController.navigate(
                Routes.ViewMore(
                    "now_playing",
                    "In Cinemas"
                )
            )
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }

    }
}

@Composable
fun ImageTextComponent(data: Result?, isLoading: Boolean, navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {


        CardComponent(data = data, isLoading = isLoading, navController = navController)


        Text(
            text = data?.overview ?: " ",
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            maxLines = 8
        )

    }
}
