package com.aystudio.watchlist.presentation.models


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverters {
    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>?): String? {
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toGenreIds(genreIdsString: String?): List<Int>? {
        return genreIdsString?.let {
            val listType = object : TypeToken<List<Int>>() {}.type
            Gson().fromJson(genreIdsString, listType)
        }
    }
}