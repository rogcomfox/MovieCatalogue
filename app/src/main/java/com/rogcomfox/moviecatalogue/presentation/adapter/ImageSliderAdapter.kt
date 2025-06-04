package com.rogcomfox.moviecatalogue.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.rogcomfox.core.source.local.entity.TrendingEntity
import com.rogcomfox.core.source.remote.network.Routing
import com.rogcomfox.moviecatalogue.R
import com.rogcomfox.moviecatalogue.databinding.SliderImageLayoutBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter(private val imgList: List<TrendingEntity>) :
    SliderViewAdapter<ImageSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder? {
        val binding =
            SliderImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            with(imgList[position]) {
                binding.imgTrendingList.load("${Routing.IMAGE_BASE_URL}${this.backDropPath}") {
                    crossfade(true)
                    placeholder(R.mipmap.ic_launcher)
                }
            }
        }
    }

    override fun getCount(): Int = imgList.size

    inner class ViewHolder(val binding: SliderImageLayoutBinding) :
        SliderViewAdapter.ViewHolder(binding.root)
}