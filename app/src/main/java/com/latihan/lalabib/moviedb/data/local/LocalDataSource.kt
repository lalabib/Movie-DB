package com.latihan.lalabib.moviedb.data.local

import androidx.lifecycle.LiveData
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.NowPlayingMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.PopularMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity
import com.latihan.lalabib.moviedb.data.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getPopularMovie(): LiveData<List<PopularMovieEntity>> = movieDao.getPopularMovie()

    fun getTopRatedMovie(): LiveData<List<TopRatedMovieEntity>> = movieDao.getTopRatedMovie()

    fun getNowPlayingMovie(): LiveData<List<NowPlayingMovieEntity>> = movieDao.getNowPlayingMovie()

    fun insertPopularMovie(movie: List<PopularMovieEntity>) = movieDao.insertPopularMovies(movie)

    fun insertTopRatedMovie(movie: List<TopRatedMovieEntity>) = movieDao.insertTopRatedMovies(movie)

    fun insertNowPlayingMovie(movie: List<NowPlayingMovieEntity>) = movieDao.insertNowPlayingMovies(movie)

    fun getDetailMovie(id: String): LiveData<MovieEntity> = movieDao.getDetailMovie(id)

    fun updateMovie(movie: MovieEntity) {
        movieDao.updateMovie(movie)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }
}