package com.latihan.lalabib.moviedb.networking

import com.latihan.lalabib.moviedb.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey: String): Call<PopularMovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("api_key") apiKey: String): Call<TopRatedMovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Query("api_key") apiKey: String): Call<NowPlayingMovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("movie/{movie_id}/reviews")
    fun getReview(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<ReviewResponse>
}