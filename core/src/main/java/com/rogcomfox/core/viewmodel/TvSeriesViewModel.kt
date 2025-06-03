package com.rogcomfox.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogcomfox.core.repo.TvSeriesRepo
import com.rogcomfox.core.source.Resource
import com.rogcomfox.core.source.remote.response.TvSeriesListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvSeriesViewModel(private val tvSeriesRepo: TvSeriesRepo) : ViewModel() {
    private val getAiringTodaySeriesLiveData = MutableLiveData<Resource<TvSeriesListResponse>>()
    val getAiringTodaySeriesStatus = getAiringTodaySeriesLiveData

    private val getPopularSeriesLiveData = MutableLiveData<Resource<TvSeriesListResponse>>()
    val getPopularSeriesStatus = getPopularSeriesLiveData

    fun getAiringTodaySeries(page: Int) {
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
}