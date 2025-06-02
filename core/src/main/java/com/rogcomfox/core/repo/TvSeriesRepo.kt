package com.rogcomfox.core.repo

import com.rogcomfox.core.safeApiCall
import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.TvSeriesDetailEntity
import com.rogcomfox.core.source.remote.network.ApiService
import com.rogcomfox.core.source.remote.response.TvSeriesListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvSeriesRepo(private val apiService: ApiService) : TvSeriesDataSource {
    override suspend fun getAiringTodayTvSeries(page: Int): Flow<Resource<TvSeriesListResponse>> =
        flow {
            emit(safeApiCall {
                apiService.getAiringTodayTvSeries(page)
            })
        }.flowOn(Dispatchers.IO)

    override suspend fun getPopularTvSeries(page: Int): Flow<Resource<TvSeriesListResponse>> =
        flow {
            emit(safeApiCall {
                apiService.getPopularTvSeries(page)
            })
        }.flowOn(Dispatchers.IO)

    override suspend fun getDetailTvSeries(seriesId: Int): Flow<Resource<TvSeriesDetailEntity>> =
        flow {
            emit(safeApiCall {
                apiService.getDetailTvSeries(seriesId)
            })
        }.flowOn(Dispatchers.IO)

    override suspend fun searchTvSeries(
        seriesQuery: String,
        isAdultToo: Boolean,
        seriesPage: Int,
        seriesYear: Int
    ): Flow<Resource<TvSeriesListResponse>> =
        flow {
            emit(safeApiCall {
                apiService.searchTvSeries(seriesQuery, isAdultToo, seriesPage, seriesYear)
            })
        }.flowOn(Dispatchers.IO)

}