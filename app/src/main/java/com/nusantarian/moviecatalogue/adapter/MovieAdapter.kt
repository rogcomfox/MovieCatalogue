package com.nusantarian.moviecatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.model.Movie
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter internal constructor(private val context: Context) : BaseAdapter() {

    var movies = arrayListOf<Movie>()
    var moviesQuery = arrayListOf<Movie>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView =
                LayoutInflater.from(context).inflate(R.layout.layout_main_list, parent, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val movie = getItem(position) as Movie
        viewHolder.bind(movie)
        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvGenre: TextView = view.findViewById(R.id.tv_genre)
        val tvRating: TextView = view.findViewById(R.id.tv_rating)
        val imgPoster: ImageView = view.findViewById(R.id.img_poster)

        internal fun bind(movie: Movie){
            tvTitle.text = movie.title
            tvGenre.text = movie.genre
            tvRating.text = movie.rating
            Picasso.get().load(movie.poster).into(imgPoster)
        }
    }

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = movies.size

}