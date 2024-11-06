package com.aystudio.watchlist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun CustomSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {


    ElevatedCard(elevation = CardDefaults.elevatedCardElevation(4.dp), shape = CircleShape) {

        Row(modifier = Modifier.toggleable(checked, true, onValueChange = onCheckedChange)) {


            Text(
                text = "Day", modifier = Modifier
                    .width(60.dp)
                    .clip(CircleShape)
                    .background(
                        if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                    )
                    .padding(4.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Week", modifier = Modifier
                    .width(60.dp)
                    .clip(CircleShape)
                    .background(
                        if (!checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                    )
                    .padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    }

}
