package com.rogcomfox.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogcomfox.core.repo.MoviesRepo
import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.MovieDetailEntity
import com.rogcomfox.core.source.remote.response.MovieResponseWithDate
import com.rogcomfox.core.source.remote.response.MovieResponseWithoutDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepo: MoviesRepo) : ViewModel() {
    private val getNowPlayingMoviesLiveData = MutableLiveData<Resource<MovieResponseWithDate>>()
    val getNowPlayingMoviesStatus = getNowPlayingMoviesLiveData

    private val getPopularMoviesLiveData = MutableLiveData<Resource<MovieResponseWithoutDate>>()
    val getPopularMoviesStatus = getPopularMoviesLiveData

    private val getDetailMovieLiveData = MutableLiveData<Resource<MovieDetailEntity>>()
    val getDetailMovieStatus = getDetailMovieLiveData

    private val searchMoviesLiveData = MutableLiveData<Resource<MovieResponseWithoutDate>>()
    val searchMoviesStatus = searchMoviesLiveData

    fun getNowPlayingMovies(page: Int) {
        getNowPlayingMoviesStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.getNowPlayingMovies(page).collect { values ->
                getNowPlayingMoviesStatus.postValue(values)
            }
        }
    }

    fun getPopularMovies(page: Int) {
        getPopularMoviesStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.getPopularMovies(page).collect { values ->
                getPopularMoviesStatus.postValue(values)
            }
        }
    }

    fun getDetailMovie(movieId: Int) {
        getDetailMovieStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.getDetailMovie(movieId).collect { values ->
                getDetailMovieStatus.postValue(values)
            }
        }
    }

    fun searchMovies(movieQuery: String, isAdultToo: Boolean, moviePage: Int, movieYear: String) {
        searchMoviesStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.searchMovies(movieQuery, isAdultToo, moviePage, movieYear).collect { values ->
                searchMoviesStatus.postValue(values)
            }
        }
    }

}