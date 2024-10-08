package com.vipin.stepgreen.stats.domain.usecase

import com.vipin.stepgreen.core.domain.repository.DayRepository

class StatsChartPageUseCases(
    dayRepository: DayRepository
) {

    val getWeek: GetWeek = GetWeekImpl(dayRepository)
}