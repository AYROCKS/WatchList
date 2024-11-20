package com.aystudio.watchlist.presentation.components

import android.util.Log
import android.view.ViewGroup
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aystudio.watchlist.R
import com.aystudio.watchlist.presentation.models.CastModelClass
import com.aystudio.watchlist.presentation.models.Result
import com.aystudio.watchlist.presentation.models.VideoModelClass
import com.aystudio.watchlist.presentation.models.toMoviesRoomModel
import com.aystudio.watchlist.presentation.viewmodel.MoviesDatabaseViewModel
import com.aystudio.watchlist.utils.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun DetailsScreenComponentsComponent(
    modifier: Modifier,
    modelClass: Result?,
    genreName: List<String>,
    videoModelClass: VideoModelClass,
    movieCast: CastModelClass
) {

    val scrollState = rememberScrollState()


    Column(modifier = modifier.verticalScroll(scrollState)) {

        Log.d("AY", modelClass?.vote_average.toString())
        PosterDisplayComponent(modelClass, videoModelClass)

        Column(modifier = Modifier.padding(horizontal = 4.dp)) {

            Text(
                text = modelClass?.title ?: "N/A",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp)
            )
            GenreIdsRowComponent(ids = genreName)

            Row(modifier = Modifier.padding(vertical = 6.dp)) {

                PosterComponent(poster = modelClass?.poster_path)

                Spacer(modifier = Modifier.size(8.dp))

                Column {
                    AdultRatedComponent(isAdult = modelClass?.adult)
                    ReleasedDateComponent(date = modelClass?.release_date)
                    OriginalLanguage(language = modelClass?.original_language)
                    RatingComponent(rating = modelClass?.vote_average)
                }

            }

            OverviewComponent(overView = modelClass?.overview)

            Spacer(modifier = Modifier.size(8.dp))

            MovieCastComponent(movieCast = movieCast)

        }

    }


}

@Composable
fun PosterDisplayComponent(
    modelClass: Result?,
    videoModelClass: VideoModelClass,
) {

    var isPlaying by rememberSaveable { mutableStateOf(false) }
    val trailer = videoModelClass.results?.firstOrNull { it.type == "Trailer" }?.key


    Box {
        if (isPlaying && trailer != null) {

            YouTubePlayerComponent(videoId = trailer)


        } else {
            AsyncImage(
                model = Constants.IMAGE_BASE_URL + modelClass?.backdrop_path,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                error = ColorPainter(Color.Gray.copy(alpha = 0.35f)),
            )

            if (trailer != null) {

                IconButton(
                    onClick = { isPlaying = true },
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .background(Color.LightGray.copy(0.3f))
                        .align(Alignment.Center)

                ) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        tint = Color.Black,
                        contentDescription = "play"
                    )
                }
            }

        }
        RowComponent(modelClass = modelClass)


    }
}

@Composable
fun RowComponent(modelClass: Result?, viewModel: MoviesDatabaseViewModel = hiltViewModel()) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        val onBackPressedDispatcher =
            LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

        IconButton(onClick = { onBackPressedDispatcher?.onBackPressed() }) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                tint = Color.White
            )
        }

        val isBookmarked = viewModel.isBookmarked.collectAsState().value

        viewModel.checkIfBookmarked(modelClass!!.id)

        IconButton(onClick = {
            modelClass.let {
                val databaseModelClass = it.toMoviesRoomModel()
                viewModel.toggleBookmark(databaseModelClass)
            }
        }) {
            Icon(
                painter = painterResource(id = if (isBookmarked) R.drawable.bookmark else R.drawable.bookmark_hallow),
                tint = Color.White,
                contentDescription = "bookmark"
            )
        }
    }
}

@Composable
fun YouTubePlayerComponent(videoId: String) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f),
        factory = { context ->
            YouTubePlayerView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        }
    )
}


@Composable
fun GenreIdsRowComponent(ids: List<String>) {

    LazyRow {
        items(ids) {
            GenresLayoutComponent(text = it)
        }
    }

}

@Composable
fun GenresLayoutComponent(text: String) {

    val borderColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    ElevatedCard(
        shape = CircleShape,
        modifier = Modifier
            .padding(end = 6.dp, top = 4.dp, bottom = 4.dp)
            .border(width = 0.dp, color = borderColor, shape = CircleShape)
    ) {
        Text(text = text, modifier = Modifier.padding(horizontal = 6.dp))
    }

}

@Composable
fun RatingComponent(rating: Double?) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD600))

        Text(text = " $rating / 10")
    }
}

@Composable
fun PosterComponent(poster: String?) {

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier
            .height(200.dp)
            .width(160.dp)
    ) {
        AsyncImage(
            model = Constants.IMAGE_BASE_URL + poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = ColorPainter(Color.Gray.copy(alpha = 0.4f)),
            modifier = Modifier.fillMaxSize(),
            error = painterResource(id = R.drawable.image)
        )
    }

}

@Composable
fun AdultRatedComponent(isAdult: Boolean?) {

    Text(
        text = if (isAdult == true) "Adult-Rated" else "Non-rated",
        modifier = Modifier
            .background(
                if (isAdult == true) Color.Red.copy(alpha = 0.7f) else Color.Green.copy(
                    alpha = 0.5f
                )
            )
            .padding(2.dp)
    )
}

@Composable
fun ReleasedDateComponent(date: String?) {

    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Released Date: ")
            }
            append(date)
        },
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun OriginalLanguage(language: String?) {

    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Original Language: ")
            }
            append(language?.let { java.util.Locale(it).displayLanguage })
        },
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun OverviewComponent(overView: String?) {

    Text(
        text = overView ?: "N/A",
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray.copy(alpha = 0.1f))
            .padding(8.dp),
        textAlign = TextAlign.Start
    )

}


@Composable
fun MovieCastComponent(movieCast: CastModelClass) {

    val cast = movieCast.cast?.filter { it.known_for_department == "Acting" }

    if (cast != null) {
        CastListComponent(cast = cast)
    }

}

