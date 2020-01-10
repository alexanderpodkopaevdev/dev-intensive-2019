package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.TimeUnits.*
import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Long, units: TimeUnits = SECOND) : Date {
    var time = this.time
    time += when (units) {
        SECOND -> value
        MINUTE -> value * 60
        HOUR -> value * 60 * 60
        DAY -> value * 24 * 60 * 60
    } * 1000
    return Date(time)
}

fun Date.humanizeDiff(date: Date = Date()) : String {
    val timeInSecs = (date.time - this.time) / 1000
    val timeInMinutes = timeInSecs/60
    val timeInHours = timeInMinutes/60
    val timeInDay = timeInHours/24

    return when {
        timeInSecs in 0..1 -> "только что"
        timeInSecs in 1..45 -> "несколько секунд назад"
        timeInSecs in 45..75 -> "минуту назад"
        timeInSecs in 75..45*60 -> "$timeInMinutes минут назад"
        timeInMinutes in 45..75 -> "час назад"
        timeInMinutes in 75..4*60 -> "$timeInHours часа назад"
        timeInHours in 4..19 -> "$timeInHours часов назад"
        timeInHours in 20..22 -> "$timeInHours часа назад"
        timeInHours in 22..26 -> "день назад"
        timeInDay in 1..360 -> {
            when {
                timeInDay < 5 -> "$timeInDay дня назад"
                timeInDay % 10  == 1L &&  (timeInDay % 100 < 10 ||  timeInDay % 100 > 20) -> "$timeInDay день назад"
                timeInDay % 100 < 20 -> "$timeInDay дней назад"
                timeInDay % 10  < 5L -> "$timeInDay дня назад"
                else -> "$timeInDay дней назад"
            }
        }
        timeInDay > 360 -> "более года назад"
        else -> "еще зайдет"
    }

}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY


}


