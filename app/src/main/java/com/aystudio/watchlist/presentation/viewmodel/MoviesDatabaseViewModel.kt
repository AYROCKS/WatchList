package com.aystudio.watchlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aystudio.watchlist.domain.repository.DatabaseRepository
import com.aystudio.watchlist.presentation.models.DatabaseModelClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDatabaseViewModel @Inject constructor(val repository: DatabaseRepository) :
    ViewModel() {


    private val _movies = MutableStateFlow<List<DatabaseModelClass>>(emptyList())
    val movies = _movies.asStateFlow()


    init {
        fetchMovies()
    }


    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovies().collectLatest {
                _movies.value = it
            }
        }
    }

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked = _isBookmarked.asStateFlow()

    fun checkIfBookmarked(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.isMovieDao(id)
            _isBookmarked.value = response
        }
    }

    fun toggleBookmark(model: DatabaseModelClass) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_isBookmarked.value) {
                repository.deleteMovie(model)
                _isBookmarked.value = false
            } else {
                repository.insertMovie(model)
                _isBookmarked.value = true
            }
        }
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    fun onQueryChanged(query: String){
        _searchQuery.value = query
    }


}