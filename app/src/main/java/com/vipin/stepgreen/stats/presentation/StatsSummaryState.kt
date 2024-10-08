package com.vipin.stepgreen.stats.presentation

data class StatsSummaryState(
    val isRefreshing: Boolean = false,
    val treesCollected: Int = 0,
    val stepsTaken: Long = 0L,
    val calorieBurned: Double = 0.0,
    val distanceTravelled: Double = 0.0,
    val carbonDioxideSaved: Double = 0.0,
)
