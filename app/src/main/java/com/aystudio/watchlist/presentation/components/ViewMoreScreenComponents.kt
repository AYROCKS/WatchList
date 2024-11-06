package com.aystudio.watchlist.presentation.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarComponent(
    title: String,
    isTrendingMovies: Boolean = false,
    onStateChanged: (String) -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            val onBackPress = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

            IconButton(onClick = { onBackPress?.onBackPressed() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },

        actions = {

            if(isTrendingMovies){
                var state by rememberSaveable { mutableStateOf(false) }

                CustomSwitch(checked = state) {
                    state = it
                    val result = if(state) "day" else "week"
                    onStateChanged(result)
                }

            }

        }
    )

}