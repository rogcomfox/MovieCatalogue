package com.rogcomfox.core.source.local.entity

import com.google.gson.annotations.SerializedName

data class CompaniesEntity(
    val id: String,
    @SerializedName("logo_path") val logoPath: String?,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)

data class ProdCountriesEntity(
    @SerializedName("iso_3166_1") val isoCode: String,
    val name: String
)

data class SpokenLangEntity(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso: String,
    val name: String
)

data class GenreEntity(
    val id: Int,
    val name: String
)