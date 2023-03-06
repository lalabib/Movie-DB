package com.latihan.lalabib.moviedb.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.latihan.lalabib.moviedb.data.local.entity.*

@Dao
interface MovieDao {
    @Insert
    suspend fun addToFavorite(favMovie: FavMovieEntity)

    @Query("SELECT * FROM fav_movie")
    fun getFavoriteMovie(): LiveData<List<FavMovieEntity>>

    @Query("SELECT count(*) FROM fav_movie WHERE id = :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM fav_movie WHERE id = :id")
    suspend fun removeFromFavorite(id: Int): Int
}