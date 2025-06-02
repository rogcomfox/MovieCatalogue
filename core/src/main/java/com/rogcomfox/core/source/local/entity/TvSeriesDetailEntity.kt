package com.rogcomfox.core.source.local.entity

import com.google.gson.annotations.SerializedName

data class TvSeriesDetailEntity(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("created_by") val createdBy: List<CreatedByEntity>,
    @SerializedName("episode_run_time") val episodeRunTime: List<Int>,
    @SerializedName("first_air_date") val firstAirDate: String,
    val genres: List<GenreEntity>,
    val homepage: String,
    val id: Int,
    @SerializedName("in_production") val inProd: Boolean,
    val languages: List<String>,
    @SerializedName("last_air_date") val lastAirDate: String,
    @SerializedName("last_episode_to_air") val lastEpisode: EpisodeEntity,
    val name: String,
    @SerializedName("next_episode_to_air") val nextEpisode: String?,
    val networks: List<CompaniesEntity>,
    @SerializedName("number_of_episodes") val numEpisodes: Int,
    @SerializedName("number_of_seasons") val numSeasons: Int,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originLang: String,
    @SerializedName("original_name") val originName: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val prodCompanies: List<CompaniesEntity>,
    @SerializedName("production_countries") val prodCountries: List<ProdCountriesEntity>,
    val seasons: List<SeasonEntity>,
    @SerializedName("spoken_languages") val spokenLang: List<SpokenLangEntity>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerializedName("vote_average") val voteAvg: Float,
    @SerializedName("vote_count") val voteCount: Int
)

data class CreatedByEntity(
    val id: Int,
    @SerializedName("credit_id") val creditId: String,
    val name: String,
    val gender: Int,
    @SerializedName("profile_path") val profilePath: String
)

data class EpisodeEntity(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("vote_average") val voteAvg: Int
)

data class SeasonEntity(
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode_count") val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("season_number") val seasonNumber: Int,
    @SerializedName("vote_average") val voteAvg: Float
)
