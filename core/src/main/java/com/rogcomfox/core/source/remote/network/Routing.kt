package com.rogcomfox.core.source.remote.network

object Routing {
    // Main URL
    const val MAIN_URL = "https://api.themoviedb.org/3"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/original"

    // Movie List URL
    const val GET_NOW_PLAYING_MOVIES_URL = "/movie/now_playing"
    const val GET_POPULAR_MOVIES_URL = "/movie/popular"

    // Movie Single URL
    const val GET_DETAIL_MOVIE_URL = "/movie/{movie_id}"

    // Tv Series List URL
    const val GET_AIRING_TODAY_TV_SERIES_URL = "/tv/airing_today"
    const val GET_POPULAR_TV_SERIES_URL = "/tv/popular"

    // Tv Series Single URL
    const val GET_DETAIL_TV_SERIES_URL = "/tv/{series_id}"
}