package com.dangtho.mynote.data.api

import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.N_ListUserResponse
import com.dangtho.mynote.data.model.Weather
import com.dangtho.mynote.data.model.WeatherForecast
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    companion object {
        private const val API_KEY = "62988e220a3f4b539b950740210711"
        private const val GET_FORE_CAST = "v1/forecast.json?key=$API_KEY&days=5&aqi=no&alerts=no"
        private const val GET_CURRENT = "v1/current.json?key=$API_KEY&aqi=no"
        private const val API_LOGIN = "api/login"
        private const val API_LIST_USER = "/api/users?page=2"
    }

    @GET(GET_FORE_CAST)
    suspend fun getForeCast(@Query("q") location: String): Response<WeatherForecast>

    @GET("v1/current.json?key=62988e220a3f4b539b950740210711&aqi=no")
    suspend fun getCurrentWeather(@Query("q") location: String): Response<Weather>

    @GET(API_LIST_USER)
    suspend fun getListUser(): Response<N_ListUserResponse>

    @FormUrlEncoded
    @POST(API_LOGIN)
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>
}