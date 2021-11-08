package com.dangtho.mynote.view.viewmodel

import android.util.Log
import com.dangtho.mynote.model.Weather
import com.dangtho.mynote.network.ApiCalled
import com.dangtho.mynote.network.BannersResponse
import com.dangtho.mynote.network.BaseResponse
import com.dangtho.mynote.view.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel : BaseViewModel() {
    companion object {
        const val TAG = "HomeFragmentViewModel"
    }

    private var _tepmC = MutableStateFlow<String>("")
    private var _weather = MutableStateFlow(Weather())
    val tempC: StateFlow<String> = _tepmC
    val weather: StateFlow<Weather> = _weather.asStateFlow()
    val callBack = object : Callback<Weather> {
        override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
            if (response.isSuccessful && response.body() != null) {
                _weather.value = response.body()!!
                Log.e(TAG, "succss"+ weather.value.location?.countryLocation)
            }
        }

        override fun onFailure(call: Call<Weather>, t: Throwable) {
            Log.e(TAG, "error")
        }
    }

    fun getForeCastWeather() {
        CoroutineScope(Dispatchers.IO).launch {
            ApiCalled.getCurentWeather("Lang Son")?.enqueue(callBack)
        }
    }
}