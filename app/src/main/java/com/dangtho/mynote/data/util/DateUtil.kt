package com.dangtho.mynote.data.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        private val nowCal = Calendar.getInstance()
        private const val FORMAT_DATE_HOUR = "yyyy-MM-dd HH:mm"
        private const val FORMAT_DATE_HOUR_MINUTES = "yyyy-MM-dd HH:mm:ss"
        private const val FORMAT_DATE = "yyyy-MM-dd"
        private const val TODAY = "ToDay"
        private const val TOMORROW = "Tomorrow"
        private const val MON = "Mon"
        private const val TUE = "Tues"
        private const val WEND = "Wend"
        private const val THUR = "Thur"
        private const val FRI = "Fri"
        private const val SAT = "Sat"
        private const val SUN = "Sun"
        private val getDayOfWeek: (day: Int) -> String = {
            when (it) {
                2 -> MON
                3 -> TUE
                4 -> WEND
                5 -> THUR
                6 -> FRI
                7 -> SAT
                8 -> SUN
                else -> "ERROR"
            }
        }

        fun getDateFromString(date: String, formatDate: String): Calendar {
            val format = SimpleDateFormat(formatDate)
            val date = format.parse(date)
            val cal = Calendar.getInstance()
            cal.time = date
            return cal
        }

        fun getStringFromLong(time: Long): String {
            val date = Date(time)
            return date.toString()
        }

        fun getHourString(date: String): String {
            val cal = getDateFromString(date, FORMAT_DATE_HOUR)
            return "${cal.get(Calendar.HOUR_OF_DAY)}:${cal.get(Calendar.MINUTE)}"
        }

        /**
         * Search the hour is nearer
         */
        fun nearHourCurrent(date: String): StateHourCurrent {
            val hourCal = getDateFromString(date, FORMAT_DATE_HOUR)
            return when {
                hourCal.get(Calendar.HOUR_OF_DAY) < nowCal.get(Calendar.HOUR_OF_DAY) -> StateHourCurrent.BEFORE
                hourCal.get(Calendar.HOUR_OF_DAY) > nowCal.get(Calendar.HOUR_OF_DAY) -> StateHourCurrent.AFTER
                else -> StateHourCurrent.CURRENT
            }
        }

        /**
         * get today or tomorrow
         */
        fun dayString(date: String): String {
            val hourCal = getDateFromString(date, FORMAT_DATE)
            return when {
                hourCal.get(Calendar.DATE) - nowCal.get(Calendar.DATE) == 1 -> TOMORROW
                hourCal.get(Calendar.DATE) - nowCal.get(Calendar.DATE) == 0 -> TODAY
                else -> getDayOfWeek(hourCal.get(Calendar.DAY_OF_WEEK))
            }
        }

        fun getStateDay(): StateDay {
            return when {
                nowCal.get(Calendar.HOUR_OF_DAY) in 6..9 -> StateDay.MORNING
                nowCal.get(Calendar.HOUR_OF_DAY) in 10..17 -> StateDay.NOON
                else -> StateDay.NIGHT
            }
        }
    }
}

enum class StateHourCurrent {
    BEFORE, CURRENT, AFTER
}

enum class StateDay {
    MORNING, NOON, AFTERNOON, NIGHT
}
