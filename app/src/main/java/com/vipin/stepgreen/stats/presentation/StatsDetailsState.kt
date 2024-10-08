package com.vipin.stepgreen.stats.presentation

import java.time.LocalDate

data class StatsDetailsState(
    val date: LocalDate,
    val stepsTaken: Int,
    val treeCollected: Boolean,
    val calorieBurned: Int,
    val distanceTravelled: Double,
    val carbonDioxideSaved: Double,
    val chartDateRange: ClosedRange<LocalDate>
)