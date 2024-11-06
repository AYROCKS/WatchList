package com.aystudio.watchlist.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.aystudio.watchlist.R
import com.aystudio.watchlist.presentation.models.Result
import com.aystudio.watchlist.utils.Constants
import com.aystudio.watchlist.utils.Routes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun CardItemComponent(
    category: LazyPagingItems<Result>,
    isLoading: Boolean,
    navController: NavController,
    isSearchScreen: Boolean = false
) {
    if (isLoading && !isSearchScreen) {

        LazyRow {
            items(10) {
                CardComponent(data = null, isLoading = true, navController = navController)
            }
        }

    } else {
        if (!isSearchScreen) {
            LazyRow {
                items(category.itemCount) { index ->
                    CardComponent(
                        data = category[index],
                        isLoading = false,
                        navController = navController
                    )
                }
            }

        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(category.itemCount) { index ->
                    category[index]?.let { result ->
                        CardComponent(
                            data = result,
                            isLoading = false,
                            navController = navController,
                            isSearchScreen = true
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CardComponent(
    data: Result?,
    isLoading: Boolean,
    navController: NavController,
    isSearchScreen: Boolean = false
) {


    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = {
            if (!isLoading) {
                val json = Json.encodeToString(data)
                navController.navigate(Routes.DETAILS(json))
            }

        },
        modifier =
        if (!isSearchScreen) {
            Modifier
                .width(160.dp)
                .size(width = 160.dp, height = 200.dp)
                .padding(end = 8.dp)
        } else {
            Modifier
                .aspectRatio(0.8f) // 200/160
                .padding(4.dp)
        }


    ) {
        if (isLoading)
            Image(
                painter = ColorPainter(Color.Gray.copy(alpha = 0.4f)),
                contentDescription = null
            )
        else
            AsyncImage(
                model = Constants.IMAGE_BASE_URL + data?.poster_path,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.Gray.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxSize(),
                error = painterResource(id = R.drawable.image)
            )
    }

}

