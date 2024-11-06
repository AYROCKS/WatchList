package com.aystudio.watchlist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.aystudio.watchlist.presentation.components.BottomNavItems
import com.aystudio.watchlist.presentation.models.ModelClass
import com.aystudio.watchlist.presentation.navigation.AppNavGraph
import com.aystudio.watchlist.presentation.theme.WatchListTheme
import com.aystudio.watchlist.presentation.viewmodel.MoviesViewModel
import com.aystudio.watchlist.utils.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       setContent {
            WatchListTheme {

                val navController = rememberNavController()


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { NavigationBar { BottomNavItems(navController) } }) { innerPadding ->


                    AppNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = navController
                    )
                }


            }
        }
    }
}
