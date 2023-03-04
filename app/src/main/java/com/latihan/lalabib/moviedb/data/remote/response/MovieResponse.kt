package com.latihan.lalabib.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.latihan.lalabib.moviedb.data.local.entity.NowPlayingMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.PopularMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity

data class PopularMovieResponse(
    @SerializedName("results")
    val results: ArrayList<PopularMovieEntity>
)

data class TopRatedMovieResponse(
    @SerializedName("results")
    val results: ArrayList<TopRatedMovieEntity>
)

data class NowPlayingMovieResponse(
    @SerializedName("results")
    val results: ArrayList<NowPlayingMovieEntity>
)