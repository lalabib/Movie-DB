package com.latihan.lalabib.moviedb.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.moviedb.R
import com.latihan.lalabib.moviedb.data.local.entity.MovieEntity
import com.latihan.lalabib.moviedb.databinding.ItemPopularMovieBinding
import com.latihan.lalabib.moviedb.utils.IMG_URL

class PopularMovieAdapter(private val onItemClick: (MovieEntity) -> Unit) :
    ListAdapter<MovieEntity, PopularMovieAdapter.MovieViewHolder>(DIFFUTIL) {

    object DIFFUTIL : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemPopularMovieBinding.inflate(
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


    class MovieViewHolder(private val binding: ItemPopularMovieBinding, val onItemClick: (MovieEntity) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(IMG_URL + movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_img)
                    )
                    .into(ivPoster)
            }
            itemView.setOnClickListener { onItemClick(movie) }
        }
    }
}