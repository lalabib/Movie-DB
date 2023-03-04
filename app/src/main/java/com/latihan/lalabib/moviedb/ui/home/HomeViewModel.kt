package com.latihan.lalabib.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.moviedb.data.MovieRepository
import com.latihan.lalabib.moviedb.data.local.entity.NowPlayingMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.PopularMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity
import com.latihan.lalabib.moviedb.utils.Resource

class HomeViewModel(private val repository: MovieRepository): ViewModel() {

    fun getPopularMovie(): LiveData<Resource<List<PopularMovieEntity>>> = repository.getPopularMovie()

    fun getTopRatedMovie(): LiveData<Resource<List<TopRatedMovieEntity>>> = repository.getTopRatedMovie()

    fun getNowPlayingMovie(): LiveData<Resource<List<NowPlayingMovieEntity>>> = repository.getNowPlayingMovie()
}