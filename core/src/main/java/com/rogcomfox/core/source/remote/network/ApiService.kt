package com.rogcomfox.core.source.remote.network

import com.rogcomfox.core.source.local.entity.MovieDetailEntity
import com.rogcomfox.core.source.local.entity.TvSeriesDetailEntity
import com.rogcomfox.core.source.remote.response.MovieResponseWithDate
import com.rogcomfox.core.source.remote.response.MovieResponseWithoutDate
import com.rogcomfox.core.source.remote.response.TvSeriesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Movies API
    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.GET_NOW_PLAYING_MOVIES_URL)
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<MovieResponseWithDate>

    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.GET_POPULAR_MOVIES_URL)
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MovieResponseWithoutDate>

    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.GET_DETAIL_MOVIE_URL)
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailEntity>

    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.SEARCH_MOVIES_URL)
    suspend fun searchMovies(
        @Query("query") movieQuery: String,
        @Query("include_adult") isAdultToo: Boolean,
        @Query("page") moviePage: Int,
        @Query("year") movieYear: String
    ): Response<MovieResponseWithoutDate>


    // TV Series API
    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.GET_AIRING_TODAY_TV_SERIES_URL)
    suspend fun getAiringTodayTvSeries(
        @Query("page") page: Int
    ): Response<TvSeriesListResponse>

    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.GET_POPULAR_TV_SERIES_URL)
    suspend fun getPopularTvSeries(
        @Query("page") page: Int
    ): Response<TvSeriesListResponse>

    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.GET_DETAIL_TV_SERIES_URL)
    suspend fun getDetailTvSeries(
        @Path("series_id") seriesId: Int
    ): Response<TvSeriesDetailEntity>

    @Headers(
        "isAuthorize: true",
        "Accept: application/json"
    )
    @GET(Routing.SEARCH_TV_SERIES_URL)
    suspend fun searchTvSeries(
        @Query("query") tvQuery: String,
        @Query("include_adult") isAdultToo: Boolean,
        @Query("page") moviePage: Int,
        @Query("year") movieYear: String
    ): Response<TvSeriesListResponse>
}