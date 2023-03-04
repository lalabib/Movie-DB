package com.latihan.lalabib.moviedb.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie_entities")
data class PopularMovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: String,

    @ColumnInfo("poster_path")
    val poster_path: String
)

@Entity(tableName = "topRated_movie_entities")
data class TopRatedMovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: String,

    @ColumnInfo("poster_path")
    val poster_path: String
)

@Entity(tableName = "nowPlaying_movie_entities")
data class NowPlayingMovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: String,

    @ColumnInfo("poster_path")
    val poster_path: String
)

@Entity(tableName = "movie_entities")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,
)