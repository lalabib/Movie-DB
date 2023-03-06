package com.latihan.lalabib.moviedb.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.adapter.FavMovieAdapter
import com.latihan.lalabib.moviedb.databinding.ActivityFavoriteBinding
import com.latihan.lalabib.moviedb.utils.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.favorite_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        favViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
    }

    private fun setupData() {
        val favAdapter = FavMovieAdapter()

        favViewModel.getFavoriteMovie().observe(this) {
            if(it.isNotEmpty()) {
                favAdapter.submitList(it)
                binding.ivEmpty.visibility = View.GONE
                binding.tvEmpty.visibility = View.GONE
            } else{
                binding.tvEmpty.visibility = View.VISIBLE
                binding.ivEmpty.visibility = View.VISIBLE
            }
        }

        binding.apply {
            rvFavMovies.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavMovies.adapter = favAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}