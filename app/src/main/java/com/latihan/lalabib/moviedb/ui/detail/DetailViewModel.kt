package com.latihan.lalabib.moviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.moviedb.data.MovieRepository
import com.latihan.lalabib.moviedb.data.local.entity.FavMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.remote.response.ReviewResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val movieId = MutableLiveData<Int>()

    fun setMovieData(id: Int) {
        movieId.value = id
    }

    var detailMovie: LiveData<MovieEntity> =
        Transformations.switchMap(movieId) {
            repository.getDetailMovie(it)
        }

    fun setReviewsData(id: Int) {
        movieId.value = id
    }

    var reviewData: LiveData<ReviewResponse> =
        Transformations.switchMap(movieId) { movieId ->
            repository.getReview(movieId)
        }

    fun addToFavorite(id: Int, title: String, overview: String, posterPath: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavMovieEntity(
                id, title, overview, posterPath
            )
            repository.addToFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = repository.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromFavorite(id)
        }
    }
}