package com.latihan.lalabib.moviedb.networking

import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieEntity>

    @GET("movie/{movie_id}/reviews")
    fun getReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<ReviewResponse>
}