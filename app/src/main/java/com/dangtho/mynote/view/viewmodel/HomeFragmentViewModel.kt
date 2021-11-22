package com.dangtho.mynote.view.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.data.Repository.MainRepository
import com.dangtho.mynote.data.model.Weather
import com.dangtho.mynote.data.model.WeatherForecast
import com.dangtho.mynote.data.api.NetworkHelper
import com.dangtho.mynote.data.model.Resource
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {
    companion object {
        const val TAG = "HomeFragmentViewModel"
    }

    private var _weatherCurrentDay = MutableLiveData<Resource<Weather>>()
    val weatherCurrentDay: LiveData<Resource<Weather>> = _weatherCurrentDay
    private var _weatherForeCast = MutableLiveData<Resource<WeatherForecast>>()
    val weatherForeCast: LiveData<Resource<WeatherForecast>> = _weatherForeCast


    init {
        coroutine.launch {
            getForecastThree()
            getForeCastWeatherCurrentDay()
        }
    }

    private suspend fun getForecastThree() {
        withContext(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getForeCastWeather("Lang Son")?.let {
                    if (it.isSuccessful) {
                        _weatherForeCast.postValue(Resource.success(it.body()))
                    } else {
                        _weatherForeCast.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _weatherForeCast.postValue(Resource.error("No internet", null))
            }
        }
    }

    private suspend fun getForeCastWeatherCurrentDay() {
        withContext(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getCurrentWeather("Lang Son")?.let {
                    if (it.isSuccessful) {
                        _weatherCurrentDay.postValue(Resource.success(it.body()))
                    } else {
                        _weatherCurrentDay.postValue(
                            Resource.error(
                                it.errorBody().toString(),
                                null
                            )
                        )
                    }
                }
            } else {
                _weatherCurrentDay.postValue(Resource.error("No internet", null))
            }
        }
    }
}