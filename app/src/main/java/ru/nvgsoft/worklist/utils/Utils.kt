package ru.nvgsoft.worklist.utils

import java.text.SimpleDateFormat
import java.util.Date

fun convertLongToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val result = sdf.format(date)
    return result
}
fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("dd.MM.yyyy")
    return df.parse(date).time
}