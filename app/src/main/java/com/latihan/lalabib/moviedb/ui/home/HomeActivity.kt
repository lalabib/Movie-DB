package com.latihan.lalabib.moviedb.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.adapter.NowPlayingMovieAdapter
import com.latihan.lalabib.moviedb.adapter.PopularMovieAdapter
import com.latihan.lalabib.moviedb.adapter.TopRatedMovieAdapter
import com.latihan.lalabib.moviedb.databinding.ActivityHomeBinding
import com.latihan.lalabib.moviedb.ui.detail.DetailActivity
import com.latihan.lalabib.moviedb.utils.Status
import com.latihan.lalabib.moviedb.utils.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupPopularMovie()
        setupTopRatedMovie()
        setupNowPlayingMovie()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setupPopularMovie() {
        val popularMovieAdapter = PopularMovieAdapter {
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, it.id)
                startActivity(this)
            }
        }

        homeViewModel.getPopularMovie().observe(this) { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        popularMovieAdapter.submitList(movie.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, movie.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.apply {
            rvPopularMovies.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            rvPopularMovies.adapter = popularMovieAdapter
        }
    }

    private fun setupTopRatedMovie() {
        val topRatedMovieAdapter = TopRatedMovieAdapter {
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, it.id)
                startActivity(this)
            }
        }

        homeViewModel.getTopRatedMovie().observe(this) { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        topRatedMovieAdapter.submitList(movie.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, movie.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.apply {
            rvTopRatedMovies.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            rvTopRatedMovies.adapter = topRatedMovieAdapter
        }
    }

    private fun setupNowPlayingMovie() {
        val nowPlayingMovieAdapter = NowPlayingMovieAdapter {
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, it.id)
                startActivity(this)
            }
        }

        homeViewModel.getNowPlayingMovie().observe(this) { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        nowPlayingMovieAdapter.submitList(movie.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, movie.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.apply {
            rvNowPlayingMovies.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            rvNowPlayingMovies.adapter = nowPlayingMovieAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}