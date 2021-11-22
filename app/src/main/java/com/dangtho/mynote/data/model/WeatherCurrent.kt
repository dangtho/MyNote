package com.dangtho.mynote.data.model

import com.google.gson.annotations.SerializedName

class WeatherCurrent {
    @SerializedName("last_updated_epoch")
    var lastUpdatedEpochCurrent: String = ""

    @SerializedName("last_updated")
    var lastUpdatedCurrent: String = ""

    @SerializedName("temp_c")
    var tempCCurrent: String = ""

    @SerializedName("temp_f")
    var tempFCurrent: String = ""

    @SerializedName("is_day")
    var isDay: String = ""

    @SerializedName("condition")
    var condition: Condition? = null

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

    @SerializedName("feelslike_c")
    var feelslikeC: String? = null
    override fun toString(): String {
        return "WeatherCurrent(lastUpdatedEpochCurrent='$lastUpdatedEpochCurrent', lastUpdatedCurrent='$lastUpdatedCurrent', tempCCurrent='$tempCCurrent', tempFCurrent='$tempFCurrent', isDay='$isDay', condition=$condition, windMph=$windMph, windKph=$windKph, windDegree=$windDegree, windDir=$windDir, humidity=$humidity, cloud=$cloud, uv=$uv, feelslikeC=$feelslikeC)"
    }

}