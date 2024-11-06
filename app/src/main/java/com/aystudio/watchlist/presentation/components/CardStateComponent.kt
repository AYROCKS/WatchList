package com.aystudio.watchlist.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.aystudio.watchlist.presentation.models.Result
import com.aystudio.watchlist.presentation.viewmodel.MoviesViewModel
import com.aystudio.watchlist.utils.Routes
import kotlinx.coroutines.flow.retry


@Composable
fun CardDisplayComponent(
    category: LazyPagingItems<Result>,
    heading: String,
    isTrending: Boolean = false,
    navController: NavController,
    categoryCode: String,
    onToggle: (String) -> Unit = {}
) {

    var checked by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.padding(start = 6.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = heading, style = MaterialTheme.typography.headlineSmall)

            if (isTrending) {
                CustomSwitch(checked = checked, onCheckedChange = {
                    checked = it
                    val toggleResult = if (checked) "day" else "week"
                    onToggle(toggleResult)
                })
            }

            IconButton(onClick = {
                navController.navigate(
                    Routes.ViewMore(
                        categoryCode,
                        heading
                    )
                )
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.size(4.dp))


        CardStateComponent(
            category = category,
            navController = navController,
        )

        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Composable
fun CardStateComponent(
    category: LazyPagingItems<Result>,
    navController: NavController,
    isSearchScreen: Boolean = false,
) {

    when (category.loadState.refresh) {
        is LoadState.Loading -> {
            CardItemComponent(category, isLoading = true, navController, isSearchScreen)
            Log.d("AY", "Loading... ")
        }

        is LoadState.Error -> {
            val error = (category.loadState.refresh as LoadState.Error).error

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    category.retry()
                }) {
                    Text(text = "Retry")
                }
            }
        }

        is LoadState.NotLoading -> {
            CardItemComponent(category, isLoading = false, navController, isSearchScreen)
            Log.d("AY", "SUCCESS")
        }
    }
}
