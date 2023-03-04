package com.latihan.lalabib.moviedb.networking

import com.latihan.lalabib.moviedb.data.remote.response.NowPlayingMovieResponse
import com.latihan.lalabib.moviedb.data.remote.response.PopularMovieResponse
import com.latihan.lalabib.moviedb.data.remote.response.TopRatedMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey: String): Call<PopularMovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("api_key") apiKey: String): Call<TopRatedMovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Query("api_key") apiKey: String): Call<NowPlayingMovieResponse>
}