package com.rogcomfox.core.source.local.entity

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.rogcomfox.core.source.local.database.TypeConvertersHelper

data class TvSeriesListEntity(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backDropPath: String,
    @TypeConverters(TypeConvertersHelper::class) @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @TypeConverters(TypeConvertersHelper::class) @SerializedName("origin_country") val oriCountry: List<String>,
    @SerializedName("original_language") val oriLang: String,
    @SerializedName("original_name") val oriName: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    val name: String,
    @SerializedName("vote_average") val voteAvg: Float,
    @SerializedName("vote_count") val voteCount: Int
)
