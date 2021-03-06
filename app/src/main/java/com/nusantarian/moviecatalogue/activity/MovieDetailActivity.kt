package com.nusantarian.moviecatalogue.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.databinding.ActivityMovieDetailBinding
import com.nusantarian.moviecatalogue.model.Movie
import com.squareup.picasso.Picasso


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setTitle(R.string.movie_detail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setData()
    }

    private fun setData(){
        val intent = intent
        val movieParcel = intent.getParcelableExtra("Movies List") as Movie
        val resPoster = movieParcel.poster
        val resTitle = movieParcel.title
        val resGenre = movieParcel.genre
        val resRating = movieParcel.rating
        val resDesc = movieParcel.desc

        Picasso.get().load(resPoster).into(binding.imgPoster)
        binding.tvTitle.text = resTitle
        binding.tvGenre.text = resGenre
        binding.tvRating.text = resRating
        binding.tvDesc.text = resDesc
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
