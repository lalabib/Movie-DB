package com.latihan.lalabib.moviedb.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val poster_path: String? = null,

    @SerializedName("release_date")
    val release_date: String? = null,

    @SerializedName("vote_average")
    val vote_average: Double? = null,

    @SerializedName("homepage")
    val homepage: String? = null
)

@Entity(tableName = "fav_movie")
data class FavMovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("overview")
    val overview: String,

    @ColumnInfo("poster_path")
    val poster_path: String,
)