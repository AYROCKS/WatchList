package com.aystudio.watchlist.presentation.models

import androidx.room.Entity
import androidx.room.PrimaryKey

fun Result.toMoviesRoomModel(): DatabaseModelClass {
    return DatabaseModelClass(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        genre_ids = this.genre_ids,
        id = this.id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )

}

fun DatabaseModelClass.toMoviesModelClass(): Result {
    return Result(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        genre_ids = this.genre_ids,
        id = this.id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

@Entity(tableName = "movies_table")
data class DatabaseModelClass(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val genre_ids: List<Int> = emptyList(),
    @PrimaryKey val id: Int,
    val original_language: String?,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)
