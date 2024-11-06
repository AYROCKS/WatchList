package com.aystudio.watchlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import com.aystudio.watchlist.presentation.models.GenreConverters


@Database(entities = [DatabaseModelClass::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun movieDao() : Dao

}