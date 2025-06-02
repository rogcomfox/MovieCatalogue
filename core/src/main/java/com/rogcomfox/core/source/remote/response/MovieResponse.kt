package com.rogcomfox.core.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rogcomfox.core.source.local.entity.MovieListEntity

data class MovieResponseWithDate(
    val dates: MovieDateEntity,
    val page: Int,
    val results: List<MovieListEntity>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalRes: Int
)

data class MovieResponseWithoutDate(
    val page: Int,
    val results: List<MovieListEntity>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalRes: Int
)

data class MovieDateEntity(
    val maximum: String,
    val minimum: String
)