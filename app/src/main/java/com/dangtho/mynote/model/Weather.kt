package com.dangtho.mynote.model

import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("location")
    var location: Location? = null

    @SerializedName("current")
    var current: WeatherCurrent? = null

    @SerializedName("wind_mph")
    var windMph: String? = null

    @SerializedName("wind_kph")
    var windKph: String? = null

    @SerializedName("wind_degree")
    var windDegree: String? = null

    @SerializedName("wind_dir")
    var windDir: String? = null

    @SerializedName("humidity")
    var humidity: String? = null

    @SerializedName("cloud")
    var cloud: String? = null

    @SerializedName("uv")
    var uv: String? = null
}