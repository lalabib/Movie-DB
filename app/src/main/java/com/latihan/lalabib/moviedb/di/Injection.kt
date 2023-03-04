package com.latihan.lalabib.moviedb.di

import android.content.Context
import com.latihan.lalabib.moviedb.data.MovieRepository
import com.latihan.lalabib.moviedb.data.local.LocalDataSource
import com.latihan.lalabib.moviedb.data.local.room.MovieDatabase
import com.latihan.lalabib.moviedb.data.remote.RemoteDataSource
import com.latihan.lalabib.moviedb.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}