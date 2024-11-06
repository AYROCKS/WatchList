package com.aystudio.watchlist.domain.repository

import androidx.paging.PagingData
import com.aystudio.watchlist.presentation.models.GenreResponse
import com.aystudio.watchlist.presentation.models.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getMovies(category: String): Flow<PagingData<Result>>

    fun getTrendingMovies(timeWindow: String): Flow<PagingData<Result>>

    fun getSearchResult(query: String): Flow<PagingData<Result>>

    fun getMoviesGenre() : Flow<GenreResponse>
}