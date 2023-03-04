package com.latihan.lalabib.moviedb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.lalabib.moviedb.data.local.LocalDataSource
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.NowPlayingMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.PopularMovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity
import com.latihan.lalabib.moviedb.data.remote.ApiResponse
import com.latihan.lalabib.moviedb.data.remote.RemoteDataSource
import com.latihan.lalabib.moviedb.data.remote.response.*
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

            override fun createCall(): LiveData<ApiResponse<PopularMovieResponse>> {
                return remoteDataSource.getPopularMovie()
            }

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

            override fun createCall(): LiveData<ApiResponse<TopRatedMovieResponse>> {
                return remoteDataSource.getTopRatedMovie()
            }

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

            override fun createCall(): LiveData<ApiResponse<NowPlayingMovieResponse>> {
                return remoteDataSource.getNowPlayingMovie()
            }

            override fun saveCallResult(data: NowPlayingMovieResponse) {
                localDataSource.insertNowPlayingMovie(data.results)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getDetailMovie(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getDetailMovie(id)
            }

            override fun saveCallResult(data: DetailMovieResponse) {
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.releaseDate,
                    data.voteAverage,
                    data.posterPath
                )
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun getReview(id: String): LiveData<ReviewResponse> {
        val reviews = MutableLiveData<ReviewResponse>()
        remoteDataSource.getReview(id, object : RemoteDataSource.LoadReviewCallback {
            override fun reviewReceived(reviewResponse: ReviewResponse) {
                reviews.postValue(reviewResponse)
            }
        })
        return reviews
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