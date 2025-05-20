package com.nusantarian.moviecatalogue.core.source.remote.response

import com.nusantarian.moviecatalogue.core.source.local.entity.MovieDateEntity
import com.nusantarian.moviecatalogue.core.source.local.entity.MovieListEntity

data class MovieResponseWithDate(
    val dates: MovieDateEntity,
    val page: Int,
    val results: List<MovieListEntity>
)

data class MovieResponseWithoutDate(
    val page: Int,
    val results: List<MovieListEntity>
)