package com.latihan.lalabib.moviedb.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.ReviewEntity
import com.latihan.lalabib.moviedb.databinding.ActivityDetailBinding
import com.latihan.lalabib.moviedb.utils.IMG_URL
import com.latihan.lalabib.moviedb.utils.Status
import com.latihan.lalabib.moviedb.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private fun setupData() {
        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_DATA)
            if (id != null) {
                detailViewModel.setMovieData(id)
                detailViewModel.setReviewsData(id)

                detailViewModel.detailMovie.observe(this) { detailMovie ->
                    when (detailMovie.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            detailMovie.data?.let { populatedDetailMovie(it) }

                            detailViewModel.reviewData.observe(this) { review ->
                                if (review.results.isEmpty()) {
                                    binding.apply {
                                        tvReview.visibility = View.GONE
                                        tvReviewEmpty.visibility = View.VISIBLE
                                    }
                                } else {
                                    review.results.first().let { populatedReviewData(it) }
                                }
                            }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(this@DetailActivity, detailMovie.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun populatedDetailMovie(movie: MovieEntity) {
        binding.apply {
            tvTitle.text = movie.title
            tvRate.text = movie.vote_average
            tvReleaseDate.text = movie.release_date
            tvOverview.text = movie.overview

            Glide.with(this@DetailActivity)
                .load(IMG_URL + movie.poster_path)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_img))
                .into(ivPosterDetail)
        }
    }

    private fun populatedReviewData(review: ReviewEntity) {
        binding.tvReview.text = review.review
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}