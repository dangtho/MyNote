package com.dangtho.mynote.data.api

import com.dangtho.mynote.data.model.Weather
import com.dangtho.mynote.data.model.WeatherForecast
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getForeCastWeather(location: String): Response<WeatherForecast> {
        return apiService.getForeCast(location)
    }

    override suspend fun getCurrentWeather(location: String): Response<Weather> {
        return apiService.getCurrentWeather(location)
    }
}