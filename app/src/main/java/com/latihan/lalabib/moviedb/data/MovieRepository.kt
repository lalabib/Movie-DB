package com.latihan.lalabib.moviedb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.lalabib.moviedb.data.local.LocalDataSource
import com.latihan.lalabib.moviedb.data.local.entity.*
import com.latihan.lalabib.moviedb.data.remote.RemoteDataSource
import com.latihan.lalabib.moviedb.data.remote.response.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): MovieDataSource {

    override fun getPopularMovie(): LiveData<MovieResponse> {
        val popularMovie = MutableLiveData<MovieResponse>()
        remoteDataSource.getPopularMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun movieReceived(movie: MovieResponse) {
                popularMovie.postValue(movie)
            }
        })
        return popularMovie
    }

    override fun getTopRatedMovie(): LiveData<MovieResponse> {
        val topRatedMovie = MutableLiveData<MovieResponse>()
        remoteDataSource.getTopRatedMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun movieReceived(movie: MovieResponse) {
                topRatedMovie.postValue(movie)
            }
        })
        return topRatedMovie
    }

    override fun getNowPlayingMovie(): LiveData<MovieResponse> {
        val nowPlayingMovie = MutableLiveData<MovieResponse>()
        remoteDataSource.getNowPlayingMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun movieReceived(movie: MovieResponse) {
                nowPlayingMovie.postValue(movie)
            }
        })
        return nowPlayingMovie
    }

    override fun getDetailMovie(id: Int): LiveData<MovieEntity> {
        val detailMovie = MutableLiveData<MovieEntity>()
        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun detailMovieReceived(movieEntity: MovieEntity) {
                detailMovie.postValue(movieEntity)
            }
        })
        return detailMovie
    }

    override fun getReview(id: Int): LiveData<ReviewResponse> {
        val reviews = MutableLiveData<ReviewResponse>()
        remoteDataSource.getReview(id, object : RemoteDataSource.LoadReviewCallback {
            override fun reviewReceived(reviewResponse: ReviewResponse) {
                reviews.postValue(reviewResponse)
            }
        })
        return reviews
    }

    override suspend fun addToFavorite(favMovie: FavMovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavMovieEntity(
                favMovie.id,
                favMovie.title,
                favMovie.overview,
                favMovie.poster_path
            )
            localDataSource.addToFavorite(user)
        }
    }

    override suspend fun checkUser(id: Int): Int {
        return localDataSource.checkUser(id)
    }

    override fun getFavoriteMovie(): LiveData<List<FavMovieEntity>> {
        return localDataSource.getFavoriteUser()
    }

    override suspend fun removeFromFavorite(id: Int): Int {
        return localDataSource.removeFromFavorite(id)
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localDataSource)
            }
    }
}