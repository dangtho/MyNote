package com.dangtho.mynote.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.data.Repository.MainApiRepository
import com.dangtho.mynote.data.api.NetworkHelper
import com.dangtho.mynote.data.model.Weather
import com.dangtho.mynote.data.model.WeatherForecast
import com.dangtho.mynote.data.model.base.Result
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val mainApiRepository: MainApiRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {
    companion object {
        const val TAG = "HomeFragmentViewModel"
    }

    private var _weatherCurrentDay = MutableLiveData<Result<Weather>>()
    val weatherCurrentDay: LiveData<Result<Weather>> = _weatherCurrentDay
    private var _weatherForeCast = MutableLiveData<Result<WeatherForecast>>()
    val weatherForeCast: LiveData<Result<WeatherForecast>> = _weatherForeCast


    init {
        coroutine.launch {
            getForecastThree()
            getForeCastWeatherCurrentDay()
        }
    }

    private suspend fun getForecastThree() {
        withContext(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                mainApiRepository.getForeCastWeather("Lang Son")?.let {
                    if (it.isSuccessful) {
                        _weatherForeCast.postValue(Result.success(it.body()))
                    } else {
                        _weatherForeCast.postValue(Result.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _weatherForeCast.postValue(Result.error("No internet", null))
            }
        }
    }

    private suspend fun getForeCastWeatherCurrentDay() {
        withContext(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                mainApiRepository.getCurrentWeather("Lang Son")?.let {
                    if (it.isSuccessful) {
                        _weatherCurrentDay.postValue(Result.success(it.body()))
                    } else {
                        _weatherCurrentDay.postValue(
                            Result.error(
                                it.errorBody().toString(),
                                null
                            )
                        )
                    }
                }
            } else {
                _weatherCurrentDay.postValue(Result.error("No internet", null))
            }
        }
    }
}