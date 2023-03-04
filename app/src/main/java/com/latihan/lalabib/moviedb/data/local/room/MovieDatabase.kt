package com.latihan.lalabib.moviedb.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.latihan.lalabib.moviedb.data.local.entity.NowPlayingMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.PopularMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity

@Database(
    entities = [PopularMovieEntity::class, TopRatedMovieEntity::class, NowPlayingMovieEntity::class],
    version = 2,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_db"
                ).fallbackToDestructiveMigration().build()
            }
    }
}