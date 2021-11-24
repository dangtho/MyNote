package com.dangtho.mynote.data.api

import com.dangtho.mynote.data.model.*
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getForeCastWeather(location: String): Response<WeatherForecast> {
        return apiService.getForeCast(location)
    }

    override suspend fun getCurrentWeather(location: String): Response<Weather> {
        return apiService.getCurrentWeather(location)
    }

    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        return apiService.login(email, password)
    }

    override suspend fun getListUser(): Response<N_ListUserResponse> {
        return apiService.getListUser()
    }
}