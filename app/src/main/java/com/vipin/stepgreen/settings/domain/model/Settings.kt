package com.vipin.stepgreen.settings.domain.model

data class Settings(
    val dailyGoal: Int,
    val stepLength: Int,
    val height: Int,
    val weight: Int,
    val pace: Double
)
