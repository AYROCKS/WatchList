package com.aystudio.watchlist.domain.repository

import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import kotlinx.coroutines.flow.Flow


interface DatabaseRepository{

    suspend fun insertMovie(databaseModelClass: DatabaseModelClass)
    suspend fun getMovies() : Flow<List<DatabaseModelClass>>
    suspend fun isMovieDao(id: Int) : Boolean
    suspend fun deleteMovie(databaseModelClass: DatabaseModelClass)
}