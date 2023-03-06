package com.latihan.lalabib.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.moviedb.data.MovieRepository
import com.latihan.lalabib.moviedb.data.remote.response.MovieResponse

class HomeViewModel(private val repository: MovieRepository): ViewModel() {

    fun getPopularMovie(): LiveData<MovieResponse> = repository.getPopularMovie()

    fun getTopRatedMovie(): LiveData<MovieResponse> = repository.getTopRatedMovie()

    fun getNowPlayingMovie(): LiveData<MovieResponse> = repository.getNowPlayingMovie()
}