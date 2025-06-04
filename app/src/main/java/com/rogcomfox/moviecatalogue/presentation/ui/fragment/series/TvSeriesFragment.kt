package com.rogcomfox.moviecatalogue.presentation.ui.fragment.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.TrendingEntity
import com.rogcomfox.core.viewmodel.TvSeriesViewModel
import com.rogcomfox.moviecatalogue.databinding.FragmentTvSeriesBinding
import com.rogcomfox.moviecatalogue.presentation.adapter.ImageSliderAdapter
import com.rogcomfox.moviecatalogue.presentation.dialog.ProgressBarDialog
import com.rogcomfox.moviecatalogue.util.toastLong
import org.koin.android.ext.android.inject

class TvSeriesFragment : Fragment() {

    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TvSeriesViewModel by inject()
    private lateinit var progressBarDialog: ProgressBarDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init progress
        progressBarDialog = ProgressBarDialog(requireContext())

        // load data
        val bannerSeriesData = viewModel.getTrendingSeriesStatus.value
        if (bannerSeriesData == null) {
            loadBannerData()
        } else {
            val bannerData = bannerSeriesData.data!!.results.take(5)
            bindBannerData(bannerData)
        }
    }

    private fun loadBannerData() {
        viewModel.getTrendingSeries()
        viewModel.getTrendingSeriesStatus.observe(viewLifecycleOwner) { trendingSeries ->
            when (trendingSeries) {
                is Resource.Loading -> {
                    progressBarDialog.show()
                }

                is Resource.Success -> {
                    val bannerData = trendingSeries.data!!.results.take(5)
                    bindBannerData(bannerData)
                    progressBarDialog.dismiss()
                }

                is Resource.Error -> {
                    progressBarDialog.dismiss()
                    context?.toastLong(trendingSeries.message)
                }
            }
        }
    }

    private fun bindBannerData(bannerData: List<TrendingEntity>) {
        val sliderAdapter = ImageSliderAdapter(bannerData)
        with(binding.imgListTrendingSeries) {
            setSliderAdapter(sliderAdapter)
            startAutoCycle()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}