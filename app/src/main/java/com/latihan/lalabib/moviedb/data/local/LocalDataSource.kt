package com.latihan.lalabib.moviedb.data.local

import androidx.lifecycle.LiveData
import com.latihan.lalabib.moviedb.data.local.entity.*
import com.latihan.lalabib.moviedb.data.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    suspend fun addToFavorite(favMovie: FavMovieEntity) = movieDao.addToFavorite(favMovie)

    fun getFavoriteUser(): LiveData<List<FavMovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun checkUser(id: Int): Int = movieDao.checkUser(id)

    suspend fun removeFromFavorite(id: Int): Int = movieDao.removeFromFavorite(id)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }
}