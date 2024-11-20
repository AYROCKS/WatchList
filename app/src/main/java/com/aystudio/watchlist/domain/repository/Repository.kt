package com.aystudio.watchlist.domain.repository

import androidx.paging.PagingData
import com.aystudio.watchlist.presentation.models.CastModelClass
import com.aystudio.watchlist.presentation.models.GenreResponse
import com.aystudio.watchlist.presentation.models.Result
import com.aystudio.watchlist.presentation.models.VideoModelClass
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getMovies(category: String): Flow<PagingData<Result>>

    fun getTrendingMovies(timeWindow: String): Flow<PagingData<Result>>

    fun getSearchResult(query: String): Flow<PagingData<Result>>

    fun getMoviesGenre() : Flow<GenreResponse>

    fun getMovieVideo(id: Int) : Flow<VideoModelClass>

    fun getMovieCast(id: Int) : Flow<CastModelClass>
}