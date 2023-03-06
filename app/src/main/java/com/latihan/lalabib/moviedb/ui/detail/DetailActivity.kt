package com.latihan.lalabib.moviedb.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.data.local.entity.ReviewEntity
import com.latihan.lalabib.moviedb.databinding.ActivityDetailBinding
import com.latihan.lalabib.moviedb.utils.IMG_URL
import com.latihan.lalabib.moviedb.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            val id = extras.getInt(EXTRA_DATA)

            detailViewModel.setMovieData(id)
            detailViewModel.setReviewsData(id)

            showLoading(true)
            detailViewModel.detailMovie.observe(this) { detailMovie ->
                detailMovie?.let { populatedDetailMovie(it) }
                showLoading(false)
            }

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
    }

    @SuppressLint("InflateParams")
    private fun populatedDetailMovie(movie: MovieEntity) {
        val title = movie.title.toString()
        val overview = movie.overview.toString()
        val poster = movie.poster_path.toString()

        binding.apply {
            tvTitle.text = title
            tvRate.text = movie.vote_average.toString().substring(0, 3)
            tvReleaseDate.text = movie.release_date
            tvOverview.text = overview

            Glide.with(this@DetailActivity)
                .load(IMG_URL + poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_img)
                )
                .into(ivPosterDetail)
        }

        // favorite movie
        val id = movie.id!!.toInt()

        var isCheck = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count > 0) {
                    binding.icFavorite.isChecked = true
                    isCheck = true
                } else {
                    binding.icFavorite.isChecked = false
                    isCheck = false
                }
            }
        }

        binding.icFavorite.setOnClickListener {
            isCheck = !isCheck
            if (isCheck) {
                detailViewModel.addToFavorite(id, title, overview, poster)
                Toast.makeText(this@DetailActivity, R.string.add_fav, Toast.LENGTH_SHORT).show()
            } else {
                detailViewModel.removeFromFavorite(id)
                Toast.makeText(this@DetailActivity, R.string.remove_fav, Toast.LENGTH_SHORT).show()
            }
            binding.icFavorite.isChecked = isCheck
        }

        //share movie
        binding.icShare.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_share, null)

            val btnClose = view.findViewById<ImageView>(R.id.ic_close)
            val btnShare = view.findViewById<Button>(R.id.btn_share)
            val bsPoster = view.findViewById<ImageView>(R.id.iv_bs_poster)
            val bsTitle = view.findViewById<TextView>(R.id.tv_bs_title)

            bsTitle.text = title
            Glide.with(this@DetailActivity)
                .load(IMG_URL + poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_img)
                )
                .into(bsPoster)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }

            btnShare.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, movie.homepage))
                }
                val share = Intent.createChooser(shareIntent, getString(R.string.app_name))
                startActivity(share)
            }

            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
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