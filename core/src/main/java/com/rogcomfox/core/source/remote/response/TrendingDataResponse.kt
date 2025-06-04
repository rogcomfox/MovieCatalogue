package com.rogcomfox.core.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rogcomfox.core.source.local.entity.TrendingEntity

data class TrendingDataResponse(
    val page: Int,
    val results: List<TrendingEntity>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalRes: Int
)