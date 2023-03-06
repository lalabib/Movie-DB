package com.latihan.lalabib.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity

data class MovieResponse(
    @SerializedName("results")
    val results: ArrayList<MovieEntity>
)