package com.latihan.lalabib.moviedb.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.moviedb.data.MovieRepository
import com.latihan.lalabib.moviedb.data.local.entity.FavMovieEntity

class FavoriteViewModel(private val repository: MovieRepository): ViewModel() {

    fun getFavoriteMovie(): LiveData<List<FavMovieEntity>> = repository.getFavoriteMovie()


}