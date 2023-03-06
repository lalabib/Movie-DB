package com.latihan.lalabib.moviedb.data

import androidx.lifecycle.LiveData
import com.latihan.lalabib.moviedb.data.local.entity.*
import com.latihan.lalabib.moviedb.data.remote.response.MovieResponse
import com.latihan.lalabib.moviedb.data.remote.response.ReviewResponse

interface MovieDataSource {

    fun getPopularMovie(): LiveData<MovieResponse>

    fun getTopRatedMovie(): LiveData<MovieResponse>

    fun getNowPlayingMovie(): LiveData<MovieResponse>

    fun getDetailMovie(id: Int): LiveData<MovieEntity>

    fun getReview(id: Int): LiveData<ReviewResponse>

    suspend fun addToFavorite(favMovie: FavMovieEntity)

    fun getFavoriteMovie(): LiveData<List<FavMovieEntity>>

    suspend fun checkUser(id: Int): Int

    suspend fun removeFromFavorite(id: Int): Int
}