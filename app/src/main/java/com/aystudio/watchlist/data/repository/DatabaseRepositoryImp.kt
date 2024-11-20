package com.aystudio.watchlist.data.repository

import com.aystudio.watchlist.data.local.Dao
import com.aystudio.watchlist.domain.repository.DatabaseRepository
import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DatabaseRepositoryImp @Inject constructor(private val moviesDao: Dao) : DatabaseRepository {

    override suspend fun insertMovie(databaseModelClass: DatabaseModelClass) {
        moviesDao.addMovie(databaseModelClass)
    }

    override suspend fun getMovies(): Flow<List<DatabaseModelClass>>{
        return moviesDao.getMoviesRoomList()
    }

    override suspend fun isMovieDao(id: Int): Boolean {
        return moviesDao.isMovieExists(id)
    }

    override suspend fun deleteMovie(databaseModelClass: DatabaseModelClass) {
        return moviesDao.deleteMovie(databaseModelClass)
    }

}