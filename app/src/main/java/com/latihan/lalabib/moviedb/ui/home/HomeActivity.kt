package com.latihan.lalabib.moviedb.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.adapter.PopularMovieAdapter
import com.latihan.lalabib.moviedb.adapter.MovieAdapter
import com.latihan.lalabib.moviedb.databinding.ActivityHomeBinding
import com.latihan.lalabib.moviedb.ui.detail.DetailActivity
import com.latihan.lalabib.moviedb.ui.favorite.FavoriteActivity
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

        showLoading(true)
        homeViewModel.getPopularMovie().observe(this) { movie ->
            popularMovieAdapter.submitList(movie.results)
            showLoading(false)
        }

        binding.apply {
            rvPopularMovies.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            rvPopularMovies.adapter = popularMovieAdapter
        }
    }

    private fun setupTopRatedMovie() {
        val topRatedMovieAdapter = MovieAdapter {
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, it.id)
                startActivity(this)
            }
        }

        showLoading(true)
        homeViewModel.getTopRatedMovie().observe(this) { movie ->
            topRatedMovieAdapter.submitList(movie.results)
            showLoading(false)
        }

        binding.apply {
            rvTopRatedMovies.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            rvTopRatedMovies.adapter = topRatedMovieAdapter
        }
    }

    private fun setupNowPlayingMovie() {
        val nowPlayingMovieAdapter = MovieAdapter {
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, it.id)
                startActivity(this)
            }
        }

        showLoading(true)
        homeViewModel.getNowPlayingMovie().observe(this) { movie ->
            nowPlayingMovieAdapter.submitList(movie.results)
            showLoading(false)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                Intent(this@HomeActivity, FavoriteActivity::class.java).apply {
                    startActivity(this)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}