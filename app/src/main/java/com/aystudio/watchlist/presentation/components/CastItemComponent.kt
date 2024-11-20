package com.aystudio.watchlist.presentation.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aystudio.watchlist.presentation.models.Cast
import com.aystudio.watchlist.utils.Constants

@Composable
fun CastListComponent(cast: List<Cast>) {

    LazyRow {

        items(cast) {
            CardItemComponent(cast = it)
        }
    }

}

@Composable
fun CardItemComponent(cast: Cast) {


    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(4.dp)) {

        AsyncImage(
            model = Constants.IMAGE_BASE_URL + cast.profile_path,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            error = ColorPainter(Color.Gray.copy(0.35f))

        )

        Text(text = cast.name ?: "N/A", style = MaterialTheme.typography.titleSmall)

        Text(
            text = cast.character ?: "N/A",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.size(120.dp).basicMarquee(iterations = 4)
        )
    }

}