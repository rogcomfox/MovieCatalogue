package com.rogcomfox.core.repo

import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.MovieDetailEntity
import com.rogcomfox.core.source.remote.response.MovieResponseWithDate
import com.rogcomfox.core.source.remote.response.MovieResponseWithoutDate
import com.rogcomfox.core.source.remote.response.TrendingDataResponse
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {
    suspend fun getNowPlayingMovies(page: Int): Flow<Resource<MovieResponseWithDate>>
    suspend fun getPopularMovies(page: Int): Flow<Resource<MovieResponseWithoutDate>>
    suspend fun getDetailMovie(movieId: Int): Flow<Resource<MovieDetailEntity>>
    suspend fun searchMovies(
        movieQuery: String,
        isAdultToo: Boolean,
        moviePage: Int,
        movieYear: String
    ): Flow<Resource<MovieResponseWithoutDate>>
    suspend fun trendingMovies(): Flow<Resource<TrendingDataResponse>>
}