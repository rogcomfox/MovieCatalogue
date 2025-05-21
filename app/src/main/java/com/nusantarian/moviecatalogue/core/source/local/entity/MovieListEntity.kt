package com.nusantarian.moviecatalogue.core.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.nusantarian.moviecatalogue.core.source.local.database.Constant
import com.nusantarian.moviecatalogue.core.source.local.database.TypeConvertersHelper

@Entity(tableName = Constant.FAV_TABLE)
data class MovieListEntity(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backDropPath: String,
    @TypeConverters(TypeConvertersHelper::class) @SerializedName("genre_ids") val genreIds: List<Int>,
    @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("original_language") val oriLang: String,
    @SerializedName("original_title") val oriTitle: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAvg: Float,
    @SerializedName("vote_count") val voteCount: Int
)