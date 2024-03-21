package com.bitspan.rishabhappzslogicass.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitspan.rishabhappzslogicass.data.Constant
import com.bitspan.rishabhappzslogicass.data.modal.response.MovieData
import com.bitspan.rishabhappzslogicass.databinding.ItemMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MovieImageAdapter(
    private val movieList: List<MovieData>,
    private val context: Context
): RecyclerView.Adapter<MovieImageAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieData) {
            val imgName = if(movie.backdropPath.isNullOrEmpty()) movie.posterPath else movie.backdropPath
            Glide.with(context)
                .load("${Constant.imgPath}$imgName")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.movieImg)


            binding.movieTitleTxt.text = movie.title
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item)

    }


    override fun getItemCount(): Int = movieList.size
}