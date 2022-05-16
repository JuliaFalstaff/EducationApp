package com.example.educationapp.utils

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

fun formateToDate(date: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        date.format(pattern)
        Log.d("TAG", "today: $date")

    } else {
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
        formatter.parse(date).toString()
    }
}

@SuppressLint("SimpleDateFormat")
fun formatStringDateToTime(date: String): String {
    return date.drop(11)
}

