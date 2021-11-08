package com.dangtho.mynote.model

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

}