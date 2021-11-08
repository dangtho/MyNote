package com.dangtho.mynote.network

import com.dangtho.mynote.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    companion object {
        private const val API_KEY = "62988e220a3f4b539b950740210711"
        private const val GET_FORE_CAST = "v1/forecast.json?key=$API_KEY&days=5&aqi=no&alerts=no"
        private const val GET_CURRENT = "v1/current.json?key=$API_KEY&aqi=no"
        private const val GET_BANNERS = "v2/home/banners/v2"
    }
    @GET(GET_BANNERS)
    fun getBanners(): Call<BaseResponse<BannersResponse>>
    @GET(GET_FORE_CAST)
    fun getForeCast(@Query("q") location: String): Call<Weather>

    @GET("v1/current.json?key=62988e220a3f4b539b950740210711&aqi=no")
    fun getCurrentWeather(@Query("q") location: String): Call<Weather>
}