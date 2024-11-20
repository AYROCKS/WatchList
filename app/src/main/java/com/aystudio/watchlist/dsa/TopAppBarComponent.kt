package com.aystudio.watchlist.dsa

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.aystudio.watchlist.R

@Composable
fun DropDownListComponent(onClick: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(
            onClick = { expanded = !expanded }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "filter"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onClick("QuickSort")

                },
                text = {
                    Text(text = "Popularity (Quick Sort)")
                }
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onClick("Greedy")
                },
                text = {
                    Text(text = "Vote Count (Greedy)")
                }
            )
        }

    }
}
