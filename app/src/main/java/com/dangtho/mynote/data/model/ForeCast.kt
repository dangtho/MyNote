package com.dangtho.mynote.data.model

import com.google.gson.annotations.SerializedName

class ForeCast {

    @SerializedName("forecastday")
    var forecastDay: List<ForeCastDay>? = null


    class ForeCastDay {

        @SerializedName("date")
        var date: String = ""

        @SerializedName("date_epoch")
        var dateEpoch: String = ""

        @SerializedName("day")
        var day: Day? = null

        @SerializedName("astro")
        var astro: Astro? = null

        @SerializedName("hour")
        var foreCastHour: List<ForeCastHour>? = null


        class Day {
            @SerializedName("maxtemp_c")
            var maxTempCDay: String = ""

            @SerializedName("maxtemp_f")
            var maxTempFDay: String = ""

            @SerializedName("mintemp_c")
            var minTempCDay: String = ""

            @SerializedName("mintemp_f")
            var minTempFDay: String = ""

            @SerializedName("uv")
            var uvFDay: String = ""

            @SerializedName("totalprecip_mm")
            var totalPrecipMm: String = ""

            @SerializedName("condition")
            var condition: Condition? = null
            @SerializedName("daily_will_it_rain")
            var mayBeRain: String? = null
            override fun toString(): String {
                return "Day(maxTempCDay='$maxTempCDay', maxTempFDay='$maxTempFDay', minTempCDay='$minTempCDay', minTempFDay='$minTempFDay', uvFDay='$uvFDay', totalPrecipMm='$totalPrecipMm', condition=$condition, mayBeRain=$mayBeRain)"
            }

        }

        class ForeCastHour {
            @SerializedName("time_epoch")
            var timeEpoch: Long = 0L

            @SerializedName("time")
            var time: String = ""

            @SerializedName("temp_c")
            var tempC: String = ""

            @SerializedName("temp_f")
            var tempF: String = ""

            @SerializedName("is_day")
            var isDay: Int = 0

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
        }

        override fun toString(): String {
            return "ForeCastDay(date='$date', dateEpoch='$dateEpoch', day=$day, astro=$astro, foreCastHour=$foreCastHour)"
        }

    }

    override fun toString(): String {
        return "ForeCast(forecastDay=$forecastDay)"
    }
}