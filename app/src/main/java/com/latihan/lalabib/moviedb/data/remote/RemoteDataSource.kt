package com.latihan.lalabib.moviedb.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.lalabib.moviedb.BuildConfig.apiKey
import com.latihan.lalabib.moviedb.data.remote.response.NowPlayingMovieResponse
import com.latihan.lalabib.moviedb.data.remote.response.PopularMovieResponse
import com.latihan.lalabib.moviedb.data.remote.response.TopRatedMovieResponse
import com.latihan.lalabib.moviedb.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getPopularMovie(): LiveData<ApiResponse<PopularMovieResponse>> {
        val resultPopularMovie = MutableLiveData<ApiResponse<PopularMovieResponse>>()
        ApiConfig.instance.getPopularMovie(apiKey).enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(call: Call<PopularMovieResponse>, response: Response<PopularMovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultPopularMovie.value = ApiResponse.success(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultPopularMovie
    }

    fun getTopRatedMovie(): LiveData<ApiResponse<TopRatedMovieResponse>> {
        val resultTopRatedMovie = MutableLiveData<ApiResponse<TopRatedMovieResponse>>()
        ApiConfig.instance.getTopRatedMovie(apiKey).enqueue(object : Callback<TopRatedMovieResponse> {
            override fun onResponse(call: Call<TopRatedMovieResponse>, response: Response<TopRatedMovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultTopRatedMovie.value = ApiResponse.success(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TopRatedMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultTopRatedMovie
    }

    fun getNowPlayingMovie(): LiveData<ApiResponse<NowPlayingMovieResponse>> {
        val resultNowPlayingMovie = MutableLiveData<ApiResponse<NowPlayingMovieResponse>>()
        ApiConfig.instance.getNowPlayingMovie(apiKey).enqueue(object : Callback<NowPlayingMovieResponse> {
            override fun onResponse(call: Call<NowPlayingMovieResponse>, response: Response<NowPlayingMovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultNowPlayingMovie.value = ApiResponse.success(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NowPlayingMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultNowPlayingMovie
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