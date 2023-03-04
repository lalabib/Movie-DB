package com.latihan.lalabib.moviedb.data

import androidx.lifecycle.LiveData
import com.latihan.lalabib.moviedb.data.local.LocalDataSource
import com.latihan.lalabib.moviedb.data.local.entity.NowPlayingMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.PopularMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity
import com.latihan.lalabib.moviedb.data.remote.ApiResponse
import com.latihan.lalabib.moviedb.data.remote.RemoteDataSource
import com.latihan.lalabib.moviedb.data.remote.response.NowPlayingMovieResponse
import com.latihan.lalabib.moviedb.data.remote.response.PopularMovieResponse
import com.latihan.lalabib.moviedb.data.remote.response.TopRatedMovieResponse
import com.latihan.lalabib.moviedb.utils.AppExecutors
import com.latihan.lalabib.moviedb.utils.Resource

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): MovieDataSource {

    override fun getPopularMovie(): LiveData<Resource<List<PopularMovieEntity>>> {
        return object : NetworkBoundResource<List<PopularMovieEntity>, PopularMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<PopularMovieEntity>> {
                return localDataSource.getPopularMovie()
            }

            override fun shouldFetch(data: List<PopularMovieEntity>?): Boolean =
                //data == null || data.isEmpty()
                true //replace it with true if you want to always retrieve data from the internet

            override fun createCall(): LiveData<ApiResponse<PopularMovieResponse>> =
                remoteDataSource.getPopularMovie()


            override fun saveCallResult(data: PopularMovieResponse) {
                localDataSource.insertPopularMovie(data.results)
            }
        }.asLiveData()
    }

    override fun getTopRatedMovie(): LiveData<Resource<List<TopRatedMovieEntity>>> {
        return object : NetworkBoundResource<List<TopRatedMovieEntity>, TopRatedMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TopRatedMovieEntity>> {
                return localDataSource.getTopRatedMovie()
            }

            override fun shouldFetch(data: List<TopRatedMovieEntity>?): Boolean =
                //data == null || data.isEmpty()
                true //replace it with true if you want to always retrieve data from the internet

            override fun createCall(): LiveData<ApiResponse<TopRatedMovieResponse>> =
                remoteDataSource.getTopRatedMovie()


            override fun saveCallResult(data: TopRatedMovieResponse) {
                localDataSource.insertTopRatedMovie(data.results)
            }
        }.asLiveData()
    }

    override fun getNowPlayingMovie(): LiveData<Resource<List<NowPlayingMovieEntity>>> {
        return object : NetworkBoundResource<List<NowPlayingMovieEntity>, NowPlayingMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<NowPlayingMovieEntity>> {
                return localDataSource.getNowPlayingMovie()
            }

            override fun shouldFetch(data: List<NowPlayingMovieEntity>?): Boolean =
                //data == null || data.isEmpty()
                true //replace it with true if you want to always retrieve data from the internet

            override fun createCall(): LiveData<ApiResponse<NowPlayingMovieResponse>> =
                remoteDataSource.getNowPlayingMovie()


            override fun saveCallResult(data: NowPlayingMovieResponse) {
                localDataSource.insertNowPlayingMovie(data.results)
            }
        }.asLiveData()
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }
}