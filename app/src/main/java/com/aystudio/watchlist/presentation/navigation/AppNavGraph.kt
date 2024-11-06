package com.aystudio.watchlist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aystudio.watchlist.presentation.screens.BookMarkScreen
import com.aystudio.watchlist.presentation.screens.DetailsScreen
import com.aystudio.watchlist.presentation.screens.MoviesScreen
import com.aystudio.watchlist.presentation.screens.SearchScreen
import com.aystudio.watchlist.presentation.screens.ViewMoreScreen
import com.aystudio.watchlist.utils.Routes

@Composable
fun AppNavGraph(modifier: Modifier, navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Routes.MOVIES) {

        composable<Routes.MOVIES> {
            MoviesScreen(navController = navHostController)
        }

        composable<Routes.SEARCH> {
            SearchScreen(modifier = modifier, navController = navHostController)
        }

        composable<Routes.FAVOURITES> {
            BookMarkScreen(modifier = modifier, navController = navHostController)
        }
        
        composable<Routes.DETAILS> {
            val args = it.toRoute<Routes.DETAILS>()

            DetailsScreen(modifier = modifier, args.result)
        }

        composable<Routes.ViewMore> {
            val args = it.toRoute<Routes.ViewMore>()
            ViewMoreScreen(modifier = modifier, navController = navHostController, category = args.category, heading = args.heading)
        }
    }
}