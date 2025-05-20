package com.nusantarian.moviecatalogue.core.source.remote.network

object Routing {
    // Main URL
    const val MAIN_URL = "https://api.themoviedb.org/3"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/original"

    // Movie List URL
    const val GET_NOW_PLAYING_MOVIES_URL = "/movie/now_playing"
    const val GET_POPULAR_MOVIES_URL = "/movie/popular"
    const val GET_TOP_RATED_MOVIES_URL = "/movie/top_rated"
    const val GET_UPCOMING_MOVIES_URL = "/movie/upcoming"

    // Movie Single URL
    const val GET_DETAIL_MOVIE_URL = "/movie/{movie_id}"
}