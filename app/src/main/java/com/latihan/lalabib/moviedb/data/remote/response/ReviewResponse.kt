package com.latihan.lalabib.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.latihan.lalabib.moviedb.data.local.entity.ReviewEntity

data class ReviewResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("results")
    val results: ArrayList<ReviewEntity>
)