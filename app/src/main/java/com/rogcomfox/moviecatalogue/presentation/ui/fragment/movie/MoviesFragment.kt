package com.rogcomfox.moviecatalogue.presentation.ui.fragment.movie

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.TrendingEntity
import com.rogcomfox.core.viewmodel.MovieViewModel
import com.rogcomfox.moviecatalogue.R
import com.rogcomfox.moviecatalogue.databinding.FragmentMoviesBinding
import com.rogcomfox.moviecatalogue.presentation.adapter.ImageSliderAdapter
import com.rogcomfox.moviecatalogue.presentation.dialog.ProgressBarDialog
import com.rogcomfox.moviecatalogue.util.toastLong
import com.rogcomfox.moviecatalogue.util.toastShort
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private var counter = 0
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by inject()
    private lateinit var progressBarDialog: ProgressBarDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init progress bar
        progressBarDialog = ProgressBarDialog(requireContext())

        // try to load data
        val bannerData = viewModel.getTrendingMoviesStatus.value
        val nowPlayingData = viewModel.getNowPlayingMoviesStatus.value
        val popularData = viewModel.getPopularMoviesStatus.value
        if (bannerData == null || nowPlayingData == null || popularData == null) {
            loadMoviesBanner()
        } else {
            val bannerTrendingData = bannerData.data?.results!!.take(5)
            bindBanner(bannerTrendingData)
        }

        // listen to on back pressed
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (counter == 0) {
                        counter += 1
                        context?.toastShort(resources.getString(R.string.text_press_back_again))
                        Handler(Looper.myLooper()!!).postDelayed({
                            counter = 0
                        }, 3000)
                    } else {
                        requireActivity().finishAffinity()
                    }
                }
            })

    }

    private fun loadMoviesBanner() {
        viewModel.getTrendingMovies()
        viewModel.getTrendingMoviesStatus.observe(viewLifecycleOwner) { trendingMovies ->
            when (trendingMovies) {
                is Resource.Loading -> {
                    progressBarDialog.show()
                }

                is Resource.Success -> {
                    val trendingMoviesData = trendingMovies.data!!.results.take(5)
                    bindBanner(trendingMoviesData)
                    loadNowPlayingMovies()
                }

                is Resource.Error -> {
                    progressBarDialog.dismiss()
                    context?.toastLong(trendingMovies.message)
                }
            }
        }
    }

    private fun loadNowPlayingMovies() {
        viewModel.getNowPlayingMovies(1)
        viewModel.getNowPlayingMoviesStatus.observe(viewLifecycleOwner) { nowPlayingMovies ->
            when (nowPlayingMovies) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    Log.e("Is Data Now Playing Movies?", nowPlayingMovies.data.toString())
                    loadPopularMovies()
                }

                is Resource.Error -> {
                    progressBarDialog.dismiss()
                    context?.toastLong(nowPlayingMovies.message)
                }
            }
        }
    }

    private fun loadPopularMovies() {
        viewModel.getPopularMovies(1)
        viewModel.getPopularMoviesStatus.observe(viewLifecycleOwner) { popularMovies ->
            when (popularMovies) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    Log.e("Is Data Popular Movies?", popularMovies.data.toString())
                    progressBarDialog.dismiss()
                }

                is Resource.Error -> {
                    progressBarDialog.dismiss()
                    context?.toastLong(popularMovies.message)
                }
            }
        }
    }

    private fun bindBanner(trendingData: List<TrendingEntity>) {
        val sliderAdapter = ImageSliderAdapter(trendingData)
        with(binding.imgListTrendingMovies) {
            setSliderAdapter(sliderAdapter)
            startAutoCycle()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}