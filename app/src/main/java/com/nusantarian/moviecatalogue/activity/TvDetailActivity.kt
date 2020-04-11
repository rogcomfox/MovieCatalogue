package com.nusantarian.moviecatalogue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.databinding.ActivityTvDetailBinding
import com.nusantarian.moviecatalogue.model.Movie
import com.squareup.picasso.Picasso

class TvDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setTitle(R.string.tv_detail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setData()
    }

    private fun setData(){
        val intent = intent
        val movieParcel = intent.getParcelableExtra("Tv List") as Movie
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
