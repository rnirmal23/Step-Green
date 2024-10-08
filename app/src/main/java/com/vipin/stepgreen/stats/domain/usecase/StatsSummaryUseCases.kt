package com.vipin.stepgreen.stats.domain.usecase

import com.vipin.stepgreen.core.domain.repository.DayRepository

class StatsSummaryUseCases(
    dayRepository: DayRepository
) {

    val getSummary: GetSummary = GetSummaryImpl(dayRepository)
}