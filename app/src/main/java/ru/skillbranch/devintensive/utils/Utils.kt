package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String, String> {
        val names = fullName?.trim()?.split(" ")
        val firstName = if (names?.getOrNull(0).isNullOrEmpty()) "null"
        else names?.getOrNull(0).toString()
        val lastName = names?.getOrNull(1).toString()
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        val first = firstName?.trim()?.getOrNull(0)?.toTitleCase()
        val last = lastName?.trim()?.getOrNull(0)?.toTitleCase()
        return when {
            first != null && last != null -> "$first$last"
            first != null -> "$first"
            last != null -> "$last"
            else -> "null"
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var newName = ""
        for (char in payload) {
            newName += when (char.toLowerCase().toString()) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                " " -> divider
                else -> char
            }
        }

        return newName.split(divider).joinToString(separator = divider) { it.capitalize() }
    }


}