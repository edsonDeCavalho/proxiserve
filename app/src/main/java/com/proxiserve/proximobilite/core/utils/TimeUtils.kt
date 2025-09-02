package com.proxiserve.proximobilite.core.utils

import java.time.ZoneId
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * Created by dloriot on 07/07/2024.
 */
object TimeUtils {

    const val DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"
    const val DEFAULT_DATE_TIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss"
    const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
    const val DEFAULT_TIME_FORMAT_SEC = "HH:mm:ss"
    const val DEFAULT_TIME_FORMAT = "HH:mm"
    const val HOUR_TIME_FORMAT = "HH"
    const val MINUTE_TIME_FORMAT = "mm"
    const val DEFAULT_TIMESTAMP = "yyyyMMddHHmmss"
    val zone = ZoneId.of("Europe/Paris")

    fun dateMilisToString(dateMilis: Int): String {
        val sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.FRANCE)
        if (dateMilis != 0)
            return sdf.format(dateMilis.toLong() * 1000)
        else
            return ""
    }

    fun stringDateToLocalDate(time: String): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT, Locale.FRENCH)
        return LocalDate.parse(time, formatter)
    }

    // NOW
    fun getNowDateTimeToString(): String {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)
        return LocalDateTime.now().format(formatter).toString()
    }

    fun getNowDateTimeToLocalDateTime(): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)
        return LocalDateTime.now()
    }

    fun getNowDateToString(): String {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)
        return LocalDateTime.now().format(formatter).toString()
    }

    fun getNowTimeToString(): String {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)
        return LocalDateTime.now().format(formatter).toString()
    }

    fun getNowHourTimeToString(): String {
        val formatter = DateTimeFormatter.ofPattern(HOUR_TIME_FORMAT)
        return LocalDateTime.now().format(formatter).toString()
    }

    fun getNowMinuteTimeToString(): String {
        val formatter = DateTimeFormatter.ofPattern(MINUTE_TIME_FORMAT)
        return LocalDateTime.now().format(formatter).toString()
    }

    @Composable
    fun getNowTimer(): String {
        var time by remember {
            mutableStateOf(getNowTimeToString())
        }

        LaunchedEffect(Unit) {
            while (true) {
                time = getNowTimeToString()
                delay(1000)
            }
        }

        return time
    }

    fun stringToLocalDateTime(stringDateTime: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)
        return LocalDateTime.parse(stringDateTime, formatter);
    }

    fun stringToLocalTime(stringDateTime: String): LocalTime {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)
        return LocalTime.parse(stringDateTime, formatter);
    }

    fun stringToDuration(stringDateTime: String): Long {
        return Duration.between(LocalTime.MIN, LocalTime.parse(stringDateTime)).toMinutes()
    }

    fun removeTime(minutesToRemove: String, time: LocalDateTime): String {
        return time.minus(stringToDuration(minutesToRemove), ChronoUnit.MINUTES)
            .format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT))
            .toString()
    }

    fun timeDifference(startTime: String, endTime: String): String {
        val start: LocalTime = stringToLocalTime(startTime)
        val end: LocalTime = stringToLocalTime(endTime)
        val duration: Duration = Duration.between(start, end)
        val heure: Long = duration.seconds / 3600
        val min: Long = duration.seconds % 3600 / 60
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);

        return LocalTime.of(heure.toInt(), min.toInt()).format(formatter)
    }

    fun addTime(timeToRemove: String, time: LocalDateTime): String {

        return time.plus(stringToDuration(timeToRemove), ChronoUnit.MINUTES).toString()
    }

    fun formatTimeDigit(digit: Int): String {
        return String.format("%02d", digit)
    }

    fun setTimeValue(heure: Int, min: Int): String {
        return formatTimeDigit(heure) + ":" + formatTimeDigit(min)
    }
}