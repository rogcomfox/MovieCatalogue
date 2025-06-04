package com.rogcomfox.core.source.local.entity

import com.google.gson.annotations.SerializedName

data class TrendingEntity(
    @SerializedName("backdrop_path") val backDropPath: String,
    val id: Int,
    val name: String,
    @SerializedName("original_name") val oriName: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("media_type") val mediaType: String,
    val adult: Boolean,
    @SerializedName("original_language") val oriLang: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val popularity: Float,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("vote_average") val voteAvg: Float,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("origin_country") val oriCountry: List<String>
)
