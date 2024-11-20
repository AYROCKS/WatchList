package com.aystudio.watchlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aystudio.watchlist.domain.repository.Repository
import com.aystudio.watchlist.presentation.models.CastModelClass
import com.aystudio.watchlist.presentation.models.GenreResponse
import com.aystudio.watchlist.presentation.models.Result
import com.aystudio.watchlist.presentation.models.VideoModelClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val popularMovies = getMovies("popular")
    val topRatedMovies = getMovies("top_rated")
    val upcomingMovies = getMovies("upcoming")
    val inCinemas = getMovies("now_playing")

    init {

        getGenres()
    }

    private fun getMovies(category: String): Flow<PagingData<Result>> {
        return repository.getMovies(category)
            .cachedIn(viewModelScope)
    }


    private val _timeWindow = MutableStateFlow("week")
    private val timeWindow = _timeWindow.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val trendingMovies: Flow<PagingData<Result>> = timeWindow
        .flatMapLatest { timeWindow -> repository.getTrendingMovies(timeWindow) }
        .cachedIn(viewModelScope)

    fun setTimeWindow(newTimeWindow: String) {
        _timeWindow.value = newTimeWindow
    }


    private val _searchQuery = MutableStateFlow("")
    val searchQuery= _searchQuery.asStateFlow()


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResult: Flow<PagingData<Result>> = searchQuery
        .debounce(500)
        .flatMapLatest { query ->
            if (query.isNotBlank()) {
                repository.getSearchResult(query)
            } else {
                flowOf(PagingData.empty())
            }
        }
        .cachedIn(viewModelScope)

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }


    private val _moviesGenre = MutableStateFlow(GenreResponse(emptyList()))
    val movieGenre = _moviesGenre.asStateFlow()


    private fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMoviesGenre().collect { genreResponse ->
                _moviesGenre.value = genreResponse
            }
        }
    }

    private val _movieVideo = MutableStateFlow(VideoModelClass(emptyList()))
    val movieVideo = _movieVideo.asStateFlow()

    fun getMovieVideo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieVideo(id).collect {
                _movieVideo.value = it
            }
        }
    }


    private val _movieCast = MutableStateFlow(CastModelClass(emptyList()))
    val movieCast = _movieCast.asStateFlow()

    fun getMovieCast(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieCast(id).collect {
                _movieCast.value = it
            }
        }
    }


}