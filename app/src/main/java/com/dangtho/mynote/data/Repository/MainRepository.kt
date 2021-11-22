package com.dangtho.mynote.data.Repository

import com.dangtho.mynote.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getForeCastWeather(location: String) = apiHelper.getForeCastWeather(location)
    suspend fun getCurrentWeather(location: String) = apiHelper.getCurrentWeather(location)
}