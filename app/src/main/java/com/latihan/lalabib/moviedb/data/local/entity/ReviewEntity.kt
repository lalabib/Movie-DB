package com.latihan.lalabib.moviedb.data.local.entity

import com.google.gson.annotations.SerializedName

data class ReviewEntity(
    @SerializedName("content")
    val review: String? = null
)
