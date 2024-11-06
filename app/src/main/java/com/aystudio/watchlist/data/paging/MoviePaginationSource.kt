package com.aystudio.watchlist.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aystudio.watchlist.data.remote.ApiService
import com.aystudio.watchlist.presentation.models.Result
import retrofit2.HttpException
import java.io.IOException


class MoviesPagingSource(
    private val apiService: ApiService,
    private val category: String? = null,
    private val timeWindow: String? = null,
    private val query: String? = null
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1

        return try {

            val response =
                if (category != null) {
                    apiService.getMoviesList(category, page)
                } else if (timeWindow != null) {
                    apiService.getTrendingGenre(time_window = timeWindow, page = page)
                } else apiService.getSearchResult(query = query!!)

            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                LoadResult.Page(
                    data = data.results,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page < data.total_pages) page + 1 else null
                )
            } else {
                Log.e("AY", "API Error: ${response.code()} ${response.message()}")
                LoadResult.Error(Exception("API ERROR"))
            }
        } catch (e: IOException) {
            Log.e("AY", "IOException: ${e.localizedMessage}")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e("AY", "HttpException: ${e.localizedMessage}")
            LoadResult.Error(e)

        } catch (e: Exception) {
            Log.e("AY", "Exception: ${e.localizedMessage}")
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
