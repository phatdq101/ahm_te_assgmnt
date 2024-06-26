package dev.phatduong.ahm_tech_asignment.utils

import java.util.Locale

fun Int?.formatToThousandText(): String {
    if (this == null) {
        return "0"
    }
    if (this < 1000) {
        return "$this"
    }
    return "${String.format(Locale.getDefault(), "%.1f", this / 1000f)}k"
}