package com.rogcomfox.core.source.local.entity

import com.google.gson.annotations.SerializedName

data class CompaniesEntity(
    val id: String,
    @SerializedName("logo_path") val logoPath: String,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)