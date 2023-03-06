package com.latihan.lalabib.moviedb.data.remote

import android.util.Log
import com.latihan.lalabib.moviedb.BuildConfig.apiKey
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.remote.response.*
import com.latihan.lalabib.moviedb.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getPopularMovie(callback: LoadMovieCallback) {
        ApiConfig.instance.getPopularMovie(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.movieReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getTopRatedMovie(callback: LoadMovieCallback) {
        ApiConfig.instance.getTopRatedMovie(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.movieReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getNowPlayingMovie(callback: LoadMovieCallback) {
        ApiConfig.instance.getNowPlayingMovie(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.movieReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getDetailMovie(id: Int, callback: LoadDetailMovieCallback) {
        ApiConfig.instance.getDetailMovie(id, apiKey).enqueue(object : Callback<MovieEntity> {
            override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.detailMovieReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getReview(id: Int, callback: LoadReviewCallback) {
        ApiConfig.instance.getReview(id, apiKey).enqueue(object : Callback<ReviewResponse> {
            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.reviewReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    interface  LoadMovieCallback {
        fun movieReceived(movie: MovieResponse)
    }

    interface  LoadDetailMovieCallback {
        fun detailMovieReceived(movieEntity: MovieEntity)
    }

    interface LoadReviewCallback {
        fun reviewReceived(reviewResponse: ReviewResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }

        private const val TAG = "RemoteData"
    }
}