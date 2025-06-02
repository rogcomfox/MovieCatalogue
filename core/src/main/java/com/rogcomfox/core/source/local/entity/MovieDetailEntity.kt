package com.rogcomfox.core.source.local.entity

import com.google.gson.annotations.SerializedName

data class MovieDetailEntity(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backDropPath: String,
    @SerializedName("belongs_to_collection") val isBelongs: String?,
    val budget: Long,
    val genres: List<GenreEntity>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val oriLang: String,
    @SerializedName("original_title") val oriTitle: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val prodCompanies: List<CompaniesEntity>,
    @SerializedName("production_countries") val prodCountries: List<ProdCountriesEntity>,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    @SerializedName("spoken_languages") val spokenLang: List<SpokenLangEntity>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAvg: Float,
    @SerializedName("vote_count") val voteCount: Int
)