package com.rogcomfox.core.source.remote.network

object Routing {
    // Image URL
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"

    // Movie List URL
    const val GET_NOW_PLAYING_MOVIES_URL = "movie/now_playing"
    const val GET_POPULAR_MOVIES_URL = "movie/popular"
    const val SEARCH_MOVIES_URL = "search/movie"
    const val TRENDING_WEEKLY_MOVIES_URL = "trending/movie/week"

    // Movie Single URL
    const val GET_DETAIL_MOVIE_URL = "movie/{movie_id}"

    // Tv Series List URL
    const val GET_AIRING_TODAY_TV_SERIES_URL = "tv/airing_today"
    const val GET_POPULAR_TV_SERIES_URL = "tv/popular"
    const val SEARCH_TV_SERIES_URL = "search/tv"
    const val TRENDING_WEEKLY_SERIES_URL = "trending/tv/week"

    // Tv Series Single URL
    const val GET_DETAIL_TV_SERIES_URL = "tv/{series_id}"
}