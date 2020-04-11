package com.nusantarian.moviecatalogue.fragment

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nusantarian.moviecatalogue.R
import com.nusantarian.moviecatalogue.activity.MovieDetailActivity
import com.nusantarian.moviecatalogue.adapter.MovieAdapter
import com.nusantarian.moviecatalogue.databinding.FragmentMovieBinding
import com.nusantarian.moviecatalogue.model.Movie


class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private var movies = arrayListOf<Movie>()
    private lateinit var resName: Array<String>
    private lateinit var resGenre: Array<String>
    private lateinit var resRating: Array<String>
    private lateinit var resDesc: Array<String>
    private lateinit var resPoster: TypedArray


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMovie.setHasFixedSize(true)
        initData()
        movies.addAll(listMovie())
        initRecycler()
    }

    private fun initRecycler() {
        binding.recyclerMovie.layoutManager = LinearLayoutManager(activity)
        val movieAdapter = MovieAdapter(movies)
        binding.recyclerMovie.adapter = movieAdapter
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra("Movies List", data)
                context?.startActivity(intent)
            }
        })
    }

    private fun initData() {
        resName = resources.getStringArray(R.array.res_name)
        resGenre = resources.getStringArray(R.array.res_genre)
        resRating = resources.getStringArray(R.array.res_rating)
        resPoster = resources.obtainTypedArray(R.array.res_photo)
        resDesc = resources.getStringArray(R.array.res_desc)
    }

    private fun listMovie(): ArrayList<Movie> {
        val listMovie = ArrayList<Movie>()
        for (i in resName.indices) {
            val movie = Movie(
                resPoster.getResourceId(i, -1),
                resName[i],
                resGenre[i],
                resRating[i],
                resDesc[i]
            )
            listMovie.add(movie)
        }
        return listMovie
    }
}
