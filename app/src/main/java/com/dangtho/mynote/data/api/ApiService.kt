package com.dangtho.mynote.data.api

import com.dangtho.mynote.data.model.Weather
import com.dangtho.mynote.data.model.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    companion object {
        private const val API_KEY = "62988e220a3f4b539b950740210711"
        private const val GET_FORE_CAST = "v1/forecast.json?key=$API_KEY&days=5&aqi=no&alerts=no"
        private const val GET_CURRENT = "v1/current.json?key=$API_KEY&aqi=no"
    }

    @GET(GET_FORE_CAST)
    suspend fun getForeCast(@Query("q") location: String): Response<WeatherForecast>

    @GET("v1/current.json?key=62988e220a3f4b539b950740210711&aqi=no")
    suspend fun getCurrentWeather(@Query("q") location: String): Response<Weather>
}