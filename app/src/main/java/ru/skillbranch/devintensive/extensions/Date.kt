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
    val timeInMinutes = timeInSecs / 60
    val timeInHours = timeInMinutes / 60
    val timeInDay = timeInHours / 24
    if (timeInSecs > 0)
        return when {
            timeInSecs in 0..1 -> "только что"
            timeInSecs in 1..45 -> "несколько секунд назад"
            timeInSecs in 45..75 -> "минуту назад"
            timeInSecs in 75..45 * 60 -> "${MINUTE.plural(timeInMinutes.toInt())} назад"
            timeInMinutes in 45..75 -> "час назад"
            timeInHours in 1..22 -> "${HOUR.plural(timeInHours.toInt())} назад"
            timeInHours in 22..26 -> "день назад"
            timeInDay in 1..360 -> "${DAY.plural(timeInDay.toInt())} назад"
            timeInDay > 360 -> "более года назад"
            else -> "еще зайдет"
        } else {
        return when {
            -timeInSecs in 0..1 -> "только что"
            -timeInSecs in 1..45 -> "через несколько секунд"
            -timeInSecs in 45..75 -> "через минуту"
            -timeInSecs in 75..45 * 60 -> "через ${MINUTE.plural(-timeInMinutes.toInt())}"
            -timeInMinutes in 45..75 -> "через час"
            -timeInHours in 1..22 -> "через ${HOUR.plural(-timeInHours.toInt())}"
            -timeInHours in 22..26 -> "через день"
            -timeInDay in 1..360 -> "через ${DAY.plural(-timeInDay.toInt())}"
            -timeInDay > 360 -> "через год"
            else -> "еще зайдет"
        }

    }
}


    enum class TimeUnits {
    SECOND {
        override fun plural(value: Int) : String {
            return when {
                value == 1 -> "$value секунду"
                value < 5 -> "$value секунды"
                value % 10 == 1 && (value % 100 < 10 || value % 100 > 20) -> "$value секунду"
                value % 100 < 20 -> "$value секунд"
                value % 10 < 5 -> "$value секунды"
                else -> "$value секунд"
            }
        }
    },
    MINUTE{
        override fun plural(value: Int) : String {
            return when {
                value == 1 -> "$value минуту"
                value < 5 -> "$value минуты"
                value % 10 == 1 && (value % 100 < 10 || value % 100 > 20) -> "$value минуту"
                value % 100 < 20 -> "$value минут"
                value % 10 < 5 -> "$value минуты"
                else -> "$value минут"
            }
        }
    },
    HOUR{
        override fun plural(value: Int) : String {
            return when {
                value == 1 -> "$value час"
                value < 5 -> "$value часа"
                value % 10 == 1 && (value % 100 < 10 || value % 100 > 20) -> "$value час"
                value % 100 < 20 -> "$value часов"
                value % 10 < 5 -> "$value часа"
                else -> "$value часов"
            }
        }
    },
    DAY{
        override fun plural(value: Int) : String {
            return when {
                value == 1 -> "$value день"
                value < 5 -> "$value дня"
                value % 10 == 1 && (value % 100 < 10 || value % 100 > 20) -> "$value день"
                value % 100 < 20 -> "$value дней"
                value % 10 < 5 -> "$value дня"
                else -> "$value дней"
            }
        }
    };
        abstract fun plural(value: Int) : String


}


