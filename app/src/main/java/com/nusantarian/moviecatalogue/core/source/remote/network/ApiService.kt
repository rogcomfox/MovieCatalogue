package com.nusantarian.moviecatalogue.core.source.remote.network

import com.nusantarian.moviecatalogue.core.source.local.entity.MovieDetailEntity
import com.nusantarian.moviecatalogue.core.source.remote.response.MovieResponseWithDate
import com.nusantarian.moviecatalogue.core.source.remote.response.MovieResponseWithoutDate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("isAuthorize: true")
    @GET(Routing.GET_NOW_PLAYING_MOVIES_URL)
    suspend fun getNowPlayingMovies(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieResponseWithDate>

    @Headers("isAuthorize: true")
    @GET(Routing.GET_POPULAR_MOVIES_URL)
    suspend fun getPopularMovies(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieResponseWithoutDate>

    @Headers("isAuthorize: true")
    @GET(Routing.GET_TOP_RATED_MOVIES_URL)
    suspend fun getTopRatedMovies(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieResponseWithoutDate>

    @Headers("isAuthorize: true")
    @GET(Routing.GET_UPCOMING_MOVIES_URL)
    suspend fun getUpcomingMovies(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieResponseWithDate>

    @Headers("isAuthorize: true")
    @GET(Routing.GET_DETAIL_MOVIE_URL)
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") lang: String,
    ): Response<MovieDetailEntity>
}