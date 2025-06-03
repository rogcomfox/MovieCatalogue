package com.rogcomfox.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogcomfox.core.repo.TvSeriesRepo
import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.local.entity.TvSeriesDetailEntity
import com.rogcomfox.core.source.remote.response.TvSeriesListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvSeriesViewModel(private val tvSeriesRepo: TvSeriesRepo) : ViewModel() {
    private val getAiringTodaySeriesLiveData = MutableLiveData<Resource<TvSeriesListResponse>>()
    val getAiringTodaySeriesStatus = getAiringTodaySeriesLiveData

    private val getPopularSeriesLiveData = MutableLiveData<Resource<TvSeriesListResponse>>()
    val getPopularSeriesStatus = getPopularSeriesLiveData

    private val getDetailSeriesLiveData = MutableLiveData<Resource<TvSeriesDetailEntity>>()
    val getDetailSeriesStatus = getDetailSeriesLiveData

    private val searchSeriesLiveData = MutableLiveData<Resource<TvSeriesListResponse>>()
    val searchSeriesStatus = searchSeriesLiveData

    fun getAiringTodayTvSeries(page: Int) {
        getAiringTodaySeriesStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            tvSeriesRepo.getAiringTodayTvSeries(page).collect { values ->
                getAiringTodaySeriesStatus.postValue(values)
            }
        }
    }

    fun getPopularTvSeries(page: Int) {
        getPopularSeriesStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            tvSeriesRepo.getPopularTvSeries(page).collect { values ->
                getPopularSeriesStatus.postValue(values)
            }
        }
    }

    fun getDetailTvSeries(seriesId: Int) {
        getDetailSeriesStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            tvSeriesRepo.getDetailTvSeries(seriesId).collect { values ->
                getDetailSeriesStatus.postValue(values)
            }
        }
    }

    fun searchTvSeries(seriesQuery: String, isAdultToo: Boolean, seriesPage: Int, seriesYear: Int) {
        searchSeriesStatus.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            tvSeriesRepo.searchTvSeries(seriesQuery, isAdultToo, seriesPage, seriesYear)
                .collect { values ->
                    searchSeriesStatus.postValue(values)
                }
        }
    }
}