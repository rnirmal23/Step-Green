package com.vipin.stepgreen.stats.presentation

import com.vipin.stepgreen.core.domain.model.Day
import java.time.LocalDate

data class StatsChartState(
    val week: List<Day>,
    val dateRange: ClosedRange<LocalDate>
) {
    companion object
}

fun StatsChartState.Companion.of(currentDate: LocalDate) = StatsChartState(
    week = emptyList(),
    dateRange = currentDate..currentDate
)