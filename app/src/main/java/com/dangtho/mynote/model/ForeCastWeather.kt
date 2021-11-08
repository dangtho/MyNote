package com.dangtho.mynote.model

import com.google.gson.annotations.SerializedName

class ForeCastWeather {

    @SerializedName("forecastday")
    var forecastDay: List<ForeCastDay>? = null

    constructor(forecastDay: List<ForeCastDay>?) {
        this.forecastDay = forecastDay
    }

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
        var hourForeCast: List<HourForeCast>? = null

        constructor(
            date: String,
            dateEpoch: String,
            day: Day?,
            astro: Astro?,
            hourForeCast: List<HourForeCast>?
        ) {
            this.date = date
            this.dateEpoch = dateEpoch
            this.day = day
            this.astro = astro
            this.hourForeCast = hourForeCast
        }

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

            @SerializedName("condition")
            var condition: Condition? = null

            constructor(
                maxTempCDay: String,
                maxTempFDay: String,
                minTempCDay: String,
                minTempFDay: String,
                uvFDay: String,
                condition: Condition?
            ) {
                this.maxTempCDay = maxTempCDay
                this.maxTempFDay = maxTempFDay
                this.minTempCDay = minTempCDay
                this.minTempFDay = minTempFDay
                this.uvFDay = uvFDay
                this.condition = condition
            }
        }

        class HourForeCast {
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

            constructor(
                timeEpoch: Long,
                time: String,
                tempC: String,
                tempF: String,
                isDay: Int,
                condition: Condition?
            ) {
                this.timeEpoch = timeEpoch
                this.time = time
                this.tempC = tempC
                this.tempF = tempF
                this.isDay = isDay
                this.condition = condition
            }
        }
    }
}