package com.vipin.stepgreen.stats.domain.usecase

import com.vipin.stepgreen.core.domain.repository.DayRepository

class StatsDetailsUseCases(
    dayRepository: DayRepository
) {

    val getFirstDate: GetFirstDate = GetFirstDateImpl(dayRepository)
}