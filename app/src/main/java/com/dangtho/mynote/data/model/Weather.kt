package com.dangtho.mynote.data.model

import com.google.gson.annotations.SerializedName

open class Weather {
    @SerializedName("location")
    var location: Location? = null

    @SerializedName("current")
    var current: WeatherCurrent? = null
    override fun toString(): String {
        return "Weather(location=$location, current=$current)"
    }

}
class WeatherForecast {
    @SerializedName("location")
    var location: Location? = null

    @SerializedName("current")
    var current: WeatherCurrent? = null

    @SerializedName("forecast")
    var forecast: ForeCast? = null
    override fun toString(): String {
        return "WeatherForecast(location=$location, current=$current, forecast=$forecast)"
    }

}