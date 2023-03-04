package com.latihan.lalabib.moviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.moviedb.data.MovieRepository
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.remote.response.ReviewResponse
import com.latihan.lalabib.moviedb.utils.Resource

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val movieId = MutableLiveData<String>()

    fun setMovieData(id: String) {
        movieId.value = id
    }

    var detailMovie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) {
            repository.getDetailMovie(it)
        }

    fun setReviewsData(id: String) {
        movieId.value = id
    }

    var reviewData: LiveData<ReviewResponse> =
        Transformations.switchMap(movieId) { movieId ->
            repository.getReview(movieId)
        }
}