package com.dangtho.mynote.data.api

import com.dangtho.mynote.data.model.*
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Singleton

open interface ApiHelper {
    suspend fun getForeCastWeather(location: String): Response<WeatherForecast>?
    suspend fun getCurrentWeather(location: String): Response<Weather>?
    suspend fun login(email: String, password: String): Response<LoginResponse>
    suspend fun getListUser(): Response<N_ListUserResponse>
}