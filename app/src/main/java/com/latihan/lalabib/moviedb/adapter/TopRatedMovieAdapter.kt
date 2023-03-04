package com.latihan.lalabib.moviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.data.local.entity.TopRatedMovieEntity
import com.latihan.lalabib.moviedb.databinding.ItemMovieBinding
import com.latihan.lalabib.moviedb.utils.IMG_URL

class TopRatedMovieAdapter(private val onItemClick: (TopRatedMovieEntity) -> Unit): ListAdapter<TopRatedMovieEntity, TopRatedMovieAdapter.MovieViewHolder>(DIFFUTIL) {

    object DIFFUTIL : DiffUtil.ItemCallback<TopRatedMovieEntity>() {
        override fun areItemsTheSame(oldItem: TopRatedMovieEntity, newItem: TopRatedMovieEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TopRatedMovieEntity, newItem: TopRatedMovieEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }


    class MovieViewHolder(private val binding: ItemMovieBinding, val onItemClick: (TopRatedMovieEntity) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TopRatedMovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(IMG_URL + movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_img))
                    .into(ivPoster)
            }
            itemView.setOnClickListener { onItemClick(movie) }
        }
    }
}