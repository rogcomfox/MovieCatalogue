package com.nusantarian.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_list.view.*


class MovieAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                Picasso.get().load(movie.poster).into(img_poster)
                tv_title.text = movie.title
                tv_genre.text = movie.genre
                tv_rating.text = movie.rating
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}