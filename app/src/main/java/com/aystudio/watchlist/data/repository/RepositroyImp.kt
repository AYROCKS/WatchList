package com.aystudio.watchlist.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aystudio.watchlist.data.paging.MoviesPagingSource
import com.aystudio.watchlist.data.remote.ApiService
import com.aystudio.watchlist.domain.repository.Repository
import com.aystudio.watchlist.presentation.models.GenreResponse
import com.aystudio.watchlist.presentation.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val apiService: ApiService) : Repository {

    override fun getMovies(category: String): Flow<PagingData<Result>> {

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(apiService, category = category) }
        ).flow

    }


    override fun getTrendingMovies(timeWindow: String): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(apiService, timeWindow = timeWindow) }
        ).flow
    }

    override fun getSearchResult(query: String): Flow<PagingData<Result>> {

        return Pager(
            config = PagingConfig(pageSize = 20 , enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(apiService, query = query)}
        ).flow

    }

    override fun getMoviesGenre(): Flow<GenreResponse> = flow {

        try {
            val response = apiService.getMovieGenres()
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                emit(data)
            }
        }
        catch (e: Exception){
            Log.e("AY", "Genre Exception ${e.localizedMessage}")
        }

    }

}
