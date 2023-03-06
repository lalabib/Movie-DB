package com.latihan.lalabib.moviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.data.local.entity.FavMovieEntity
import com.latihan.lalabib.moviedb.databinding.ItemFavMovieBinding
import com.latihan.lalabib.moviedb.utils.IMG_URL

class FavMovieAdapter :
    ListAdapter<FavMovieEntity, FavMovieAdapter.MovieViewHolder>(DIFFUTIL) {

    object DIFFUTIL : DiffUtil.ItemCallback<FavMovieEntity>() {
        override fun areItemsTheSame(oldItem: FavMovieEntity, newItem: FavMovieEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavMovieEntity, newItem: FavMovieEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder( ItemFavMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class MovieViewHolder(private val binding: ItemFavMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: FavMovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvDescription.text = movie.overview
                Glide.with(itemView.context)
                    .load(IMG_URL + movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_img))
                    .into(ivPoster)
            }
        }
    }
}
