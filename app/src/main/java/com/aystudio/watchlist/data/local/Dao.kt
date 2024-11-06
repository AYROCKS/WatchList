package com.aystudio.watchlist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao{

    @Insert
    suspend fun addMovie(databaseModelClass: DatabaseModelClass)

    @Query("SELECT * FROM movies_table")
    fun getMoviesRoomList(): Flow<List<DatabaseModelClass>>

    @Delete
    suspend fun deleteMovie(databaseModelClass: DatabaseModelClass)


}