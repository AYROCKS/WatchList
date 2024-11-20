package com.aystudio.watchlist.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarComponent(modifier: Modifier = Modifier,query: String, onQueryChange: (String) -> Unit, onClear: () -> Unit) {


    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),

        value = query,
        shape = CircleShape,
        onValueChange = { onQueryChange(it) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if(query.isNotBlank())
                IconButton(onClick = { onClear()}) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
        },
        singleLine = true,
        placeholder = { Text(text = "Search Something...")}


    )

}