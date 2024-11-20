package com.aystudio.watchlist.data.remote

import com.aystudio.watchlist.presentation.models.CastModelClass
import com.aystudio.watchlist.presentation.models.GenreResponse
import com.aystudio.watchlist.presentation.models.ModelClass
import com.aystudio.watchlist.presentation.models.VideoModelClass
import com.aystudio.watchlist.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = Constants.API_KEY
    ): Response<ModelClass>



    @GET("trending/movie/{time_window}")
    suspend fun getTrendingGenre(
        @Path("time_window") time_window: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = Constants.API_KEY
    ): Response<ModelClass>


    @GET("search/movie")
    suspend fun getSearchResult(
        @Query("query") query: String,
        @Query("include_adult") include_adult: Boolean = true,
        @Query("api_key") api_key: String = Constants.API_KEY
    ) : Response<ModelClass>


    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") api_key: String = Constants.API_KEY
    ) : Response<GenreResponse>


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = Constants.API_KEY
    ): Response<VideoModelClass>


//    https://api.themoviedb.org/3/movie/{movie_id}/credits

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = Constants.API_KEY
    ): Response<CastModelClass>
}
