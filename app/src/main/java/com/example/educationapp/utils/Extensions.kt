package com.example.educationapp.utils

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun String.convertStringToDateNewApi(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
 }


@SuppressLint("SimpleDateFormat")
fun String.convertStringToDateOldApi(): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.parse(this)
}


//
fun getCurrentTime(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val nowTime = LocalDate.now()
        val current = nowTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        current.dropLast(17)
    } else {
        Calendar.getInstance().time.convertToString().dropLast(17)
    }
}

fun getCurrentDateInMillis(): Long {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    } else {
        Calendar.getInstance().timeInMillis
    }
}

fun getCurrentDate(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val nowTime = LocalDate.now()
        nowTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
    } else {
        Calendar.getInstance().time.convertToString()
    }
}


@SuppressLint("SimpleDateFormat")
fun formatStringDateToTime(date: String): String {
    return date.drop(11)
}

fun Date.convertToString(): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(this)
}

fun Date.convertToStringForTimer(): String {
    val formatter = SimpleDateFormat("dd:HH:mm", Locale.getDefault())
    return formatter.format(this)
}



