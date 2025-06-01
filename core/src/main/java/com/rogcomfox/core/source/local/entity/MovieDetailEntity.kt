package com.rogcomfox.core.source.local.entity

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieDetailEntity(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backDropPath: String,
    @SerializedName("belongs_to_collection") val isBelongs: String?,
    val budget: Long,
    val genres: List<GenreEntity>,
    val homepage: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val oriLang: String,
    @SerializedName("original_title") val oriTitle: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path") val posterPath: String,
)