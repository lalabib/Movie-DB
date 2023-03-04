package com.latihan.lalabib.moviedb.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.latihan.lalabib.moviedb.data.local.entity.*

@Dao
interface MovieDao {

    @Query("Select * From popular_movie_entities")
    fun getPopularMovie(): LiveData<List<PopularMovieEntity>>

    @Query("Select * From topRated_movie_entities")
    fun getTopRatedMovie(): LiveData<List<TopRatedMovieEntity>>

    @Query("Select * From nowPlaying_movie_entities")
    fun getNowPlayingMovie(): LiveData<List<NowPlayingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movies: List<PopularMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovies(movies: List<TopRatedMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlayingMovies(movies: List<NowPlayingMovieEntity>)

    @Transaction
    @Query("SELECT * FROM popular_movie_entities WHERE id = :id " +
            "UNION SELECT * FROM topRated_movie_entities WHERE id = :id " +
            "UNION SELECT * FROM nowPlaying_movie_entities WHERE id = :id"
    )
    fun getDetailMovie(id: String): LiveData<MovieEntity>

    @Update
    fun updateMovie(movie: MovieEntity)
}