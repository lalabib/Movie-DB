package com.latihan.lalabib.moviedb.data

import androidx.lifecycle.LiveData
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.NowPlayingMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.PopularMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity
import com.latihan.lalabib.moviedb.data.remote.response.ReviewResponse
import com.latihan.lalabib.moviedb.utils.Resource

interface MovieDataSource {

    fun getPopularMovie(): LiveData<Resource<List<PopularMovieEntity>>>

    fun getTopRatedMovie(): LiveData<Resource<List<TopRatedMovieEntity>>>

    fun getNowPlayingMovie(): LiveData<Resource<List<NowPlayingMovieEntity>>>

    fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>>

    fun getReview(id: String): LiveData<ReviewResponse>
}