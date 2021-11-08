package com.dangtho.mynote.network

import com.dangtho.mynote.common.MyNoteApplication
import com.dangtho.mynote.model.Weather
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback

class ApiCalled {
    companion object {
        private val netWorkProvider = NetWorkProvider.getInstance()

        fun getForeCastWeather(location: String): Call<Weather>? {
            return netWorkProvider?.getForeCast(location)
        }
        fun getCurentWeather(location: String): Call<Weather>? {
            return netWorkProvider?.getCurrentWeather(location)
        }
        fun getBanner(): Call<BaseResponse<BannersResponse>>? {
            return netWorkProvider?.getBanners()
        }
    }
}