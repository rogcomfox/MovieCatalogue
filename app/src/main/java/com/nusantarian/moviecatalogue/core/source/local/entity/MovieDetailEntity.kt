package com.nusantarian.moviecatalogue.core.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nusantarian.moviecatalogue.core.source.local.database.Constant

@Entity(tableName = Constant.FAV_TABLE)
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
)