package com.rogcomfox.core.repo

import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.TvSeriesDetailEntity
import com.rogcomfox.core.source.remote.response.TvSeriesListResponse
import kotlinx.coroutines.flow.Flow

interface TvSeriesDataSource {
    suspend fun getAiringTodayTvSeries(page: Int): Flow<Resource<TvSeriesListResponse>>
    suspend fun getPopularTvSeries(page: Int): Flow<Resource<TvSeriesListResponse>>
    suspend fun getDetailTvSeries(seriesId: Int): Flow<Resource<TvSeriesDetailEntity>>
    suspend fun searchTvSeries(
        seriesQuery: String,
        isAdultToo: Boolean,
        seriesPage: Int,
        seriesYear: Int
    ): Flow<Resource<TvSeriesListResponse>>
}