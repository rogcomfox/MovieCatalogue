package com.rogcomfox.core.source.remote.response

import com.rogcomfox.core.source.local.entity.TvSeriesListEntity

data class TvSeriesListResponse (
    val page: Int,
    val results: List<TvSeriesListEntity>
)