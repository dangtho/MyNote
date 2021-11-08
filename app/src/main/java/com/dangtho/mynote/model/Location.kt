package com.dangtho.mynote.model

import com.google.gson.annotations.SerializedName

class Location {

    @SerializedName("name")
    var nameLocation: String = ""

    @SerializedName("region")
    var regionLocation: String = ""

    @SerializedName("country")
    var countryLocation: String = ""

    @SerializedName("lat")
    var latLocation: String = ""

    @SerializedName("lon")
    var lonLocation: String = ""

    @SerializedName("tz_id")
    var tzIdLocation: String = ""

    @SerializedName("localtime_epoch")
    var localTimeEpochLocation: String = ""

    @SerializedName("localtime")
    var localtime: String = ""
}
