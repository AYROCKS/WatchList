package com.aystudio.watchlist.presentation.models

data class ModelClass(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
