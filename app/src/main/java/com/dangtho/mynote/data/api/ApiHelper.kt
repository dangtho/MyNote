package com.dangtho.mynote.data.api

import com.dangtho.mynote.data.model.Weather
import com.dangtho.mynote.data.model.WeatherForecast
import retrofit2.Response

open interface ApiHelper {
    suspend fun getForeCastWeather(location: String): Response<WeatherForecast>?
    suspend fun getCurrentWeather(location: String): Response<Weather>?
}