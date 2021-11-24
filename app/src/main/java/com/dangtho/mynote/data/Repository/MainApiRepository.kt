package com.dangtho.mynote.data.Repository

import com.dangtho.mynote.data.api.ApiHelper
import javax.inject.Inject

class MainApiRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getForeCastWeather(location: String) = apiHelper.getForeCastWeather(location)
    suspend fun getCurrentWeather(location: String) = apiHelper.getCurrentWeather(location)
    suspend fun login(email: String, password: String) = apiHelper.login(email, password)
    suspend fun getListUser() = apiHelper.getListUser()
}