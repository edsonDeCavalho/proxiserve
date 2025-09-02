package com.proxiserve.proximobilite.modules.accueil.domain.model

import androidx.compose.ui.text.toUpperCase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Created by dloriot on 07/07/2024.
 */
data class CalendarUiModel(
    val selectedDate: Date,
    val visibleDates: List<Date>
) {

    val startDate: Date = visibleDates.first()
    val endDate: Date = visibleDates.last()

    data class Date(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean
    ) {
        val day: String = date.format(DateTimeFormatter.ofPattern("E"))
            .substring(0,2)
            .uppercase(Locale.ROOT)
    }
}