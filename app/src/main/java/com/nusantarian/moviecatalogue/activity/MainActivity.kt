package com.nusantarian.moviecatalogue.activity

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.adapter.MovieAdapter
import com.nusantarian.moviecatalogue.databinding.ActivityMainBinding
import com.nusantarian.moviecatalogue.model.Movie

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var back = false
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var resName: Array<String>
    private lateinit var resGenre: Array<String>
    private lateinit var resRating: Array<String>
    private lateinit var resDesc: Array<String>
    private lateinit var resPoster: TypedArray
    private var movies = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setTitle(R.string.app_name)

        movieAdapter = MovieAdapter(this)
        binding.listView.adapter = movieAdapter

        initData()
        addItem()

        binding.listView.onItemClickListener = this
    }

    private fun initData() {
        resName = resources.getStringArray(R.array.res_name)
        resGenre = resources.getStringArray(R.array.res_genre)
        resRating = resources.getStringArray(R.array.res_rating)
        resPoster = resources.obtainTypedArray(R.array.res_photo)
        resDesc = resources.getStringArray(R.array.res_desc)
    }

    private fun addItem() {
        for (i in resName.indices) {
            val movie = Movie(
                resPoster.getResourceId(i, -1),
                resName[i],
                resGenre[i],
                resRating[i],
                resDesc[i]
            )
            movies.add(movie)
        }
        movieAdapter.movies = movies
    }

    override fun onBackPressed() {
        if (back) {
            super.onBackPressed()
            return
        }
        this.back = true
        Toast.makeText(this, "Tekan BACK sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            back = false
            finish()
        }, 2000)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("Movies List", movies[position])
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
