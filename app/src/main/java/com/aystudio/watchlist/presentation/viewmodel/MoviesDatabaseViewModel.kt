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
class MoviesDatabaseViewModel @Inject constructor(val repository: DatabaseRepository) : ViewModel(){


    private val _movies = MutableStateFlow<List<DatabaseModelClass>>(emptyList())
    val movies = _movies.asStateFlow()


    init {
        fetchMovies()
    }

    fun insertMovie(model: DatabaseModelClass){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovie(model)
        }
    }

    private fun fetchMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovies().collectLatest {
                _movies.value = it
            }
        }
    }


}