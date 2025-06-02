package com.rogcomfox.core.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rogcomfox.core.source.local.entity.TvSeriesListEntity

data class TvSeriesListResponse (
    val page: Int,
    val results: List<TvSeriesListEntity>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalRes: Int
)