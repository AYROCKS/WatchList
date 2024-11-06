package  com.aystudio.watchlist.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.aystudio.watchlist.presentation.components.CardItemComponent
import com.aystudio.watchlist.presentation.components.CardStateComponent
import com.aystudio.watchlist.presentation.components.MyTopAppBarComponent
import com.aystudio.watchlist.presentation.viewmodel.MoviesViewModel

@Composable
fun ViewMoreScreen(
    modifier: Modifier,
    navController: NavController,
    category: String,
    heading: String,
    viewModel: MoviesViewModel = hiltViewModel()
) {


    val data = when (category) {

        "popular" -> viewModel.popularMovies.collectAsLazyPagingItems()
        "top_rated" -> viewModel.topRatedMovies.collectAsLazyPagingItems()
        "upcoming" -> viewModel.upcomingMovies.collectAsLazyPagingItems()
        "now_playing" -> viewModel.inCinemas.collectAsLazyPagingItems()

        else -> viewModel.trendingMovies.collectAsLazyPagingItems()

    }

    Scaffold(topBar = {

        if (category != "trending") {
            MyTopAppBarComponent(title = heading)
        } else MyTopAppBarComponent(title = heading, isTrendingMovies = true) {
            viewModel.setTimeWindow(it)
            data.retry()
        }

    }) {

        Column(modifier = Modifier.padding(it)) {


            CardStateComponent(
                category = data,
                navController = navController,
                isSearchScreen = true
            )

        }
    }
}


