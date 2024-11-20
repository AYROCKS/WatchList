package com.aystudio.watchlist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(databaseModelClass: DatabaseModelClass)

    @Query("SELECT * FROM movies_table")
    fun getMoviesRoomList(): Flow<List<DatabaseModelClass>>

    @Delete
    suspend fun deleteMovie(databaseModelClass: DatabaseModelClass)

    @Query("SELECT EXISTS(SELECT 1 FROM movies_table WHERE id = :id)")
    suspend fun isMovieExists(id: Int): Boolean


}