package com.dangtho.mynote.model

import com.google.gson.annotations.SerializedName

class Astro {
    @SerializedName("sunrise")
    var sunrise: String = ""

    @SerializedName("sunset")
    var sunset: String = ""

    @SerializedName("moonrise")
    var moonrise: String = ""

    @SerializedName("moonset")
    var moonset: String = ""

    @SerializedName("moon_phase")
    var moonPhase: String = ""

    @SerializedName("moon_illumination")
    var moonIllumination: Int = 0

    constructor(
        sunrise: String,
        sunset: String,
        moonrise: String,
        moonset: String,
        moonPhase: String,
        moonIllumination: Int
    ) {
        this.sunrise = sunrise
        this.sunset = sunset
        this.moonrise = moonrise
        this.moonset = moonset
        this.moonPhase = moonPhase
        this.moonIllumination = moonIllumination
    }
}