package com.aystudio.watchlist.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aystudio.watchlist.R
import com.aystudio.watchlist.presentation.models.BottomNavModelClass
import com.aystudio.watchlist.utils.Routes

val bottomNavItemList = listOf(
    BottomNavModelClass(Routes.MOVIES, "Movies", R.drawable.movie_camera_svgrepo_com),
    BottomNavModelClass(Routes.SEARCH, "Search", R.drawable.search_svgrepo_com),
    BottomNavModelClass(Routes.FAVOURITES, "Favourites", R.drawable.heart_svgrepo_com)
)

@Composable
fun BottomNavItems(navController: NavController) {

    var selected by remember {
        mutableIntStateOf(0)
    }


    NavigationBar {

        bottomNavItemList.forEachIndexed { index, bottomNavModelClass ->

            NavigationBarItem(
                selected = selected == index,
                onClick = {
                    selected = index
                    navController.navigate(bottomNavModelClass.routes)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavModelClass.icon),
                        modifier = Modifier.size(28.dp),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(
                        text = bottomNavModelClass.title,
                        style = MaterialTheme.typography.labelLarge
                    )
                }

            )
        }
    }
}