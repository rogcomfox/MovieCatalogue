package com.rogcomfox.core.repo

import com.rogcomfox.core.safeApiCall
import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.MovieDetailEntity
import com.rogcomfox.core.source.remote.network.ApiService
import com.rogcomfox.core.source.remote.response.MovieResponseWithDate
import com.rogcomfox.core.source.remote.response.MovieResponseWithoutDate
import com.rogcomfox.core.source.remote.response.TrendingDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepo(private val apiService: ApiService) : MoviesDataSource {
    override suspend fun getNowPlayingMovies(page: Int): Flow<Resource<MovieResponseWithDate>> =
        flow {
            emit(safeApiCall {
                apiService.getNowPlayingMovies(page)
            })
        }.flowOn(Dispatchers.IO)

    override suspend fun getPopularMovies(page: Int): Flow<Resource<MovieResponseWithoutDate>> =
        flow {
            emit(safeApiCall {
                apiService.getPopularMovies(page)
            })
        }.flowOn(Dispatchers.IO)

    override suspend fun getDetailMovie(movieId: Int): Flow<Resource<MovieDetailEntity>> =
        flow {
            emit(safeApiCall {
                apiService.getDetailMovie(movieId)
            })
        }.flowOn(Dispatchers.IO)

    override suspend fun searchMovies(
        movieQuery: String,
        isAdultToo: Boolean,
        moviePage: Int,
        movieYear: String
    ): Flow<Resource<MovieResponseWithoutDate>> =
        flow {
            emit(safeApiCall {
                apiService.searchMovies(movieQuery, isAdultToo, moviePage, movieYear)
            })
        }.flowOn(Dispatchers.IO)

    override suspend fun trendingMovies(): Flow<Resource<TrendingDataResponse>> =
        flow {
            emit(safeApiCall {
                apiService.getTrendingMovies()
            })
        }.flowOn(Dispatchers.IO)
}