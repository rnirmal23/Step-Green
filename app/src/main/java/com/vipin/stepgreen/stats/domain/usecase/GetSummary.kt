package com.vipin.stepgreen.stats.domain.usecase

import com.vipin.stepgreen.core.domain.model.StatsSummary
import com.vipin.stepgreen.core.domain.model.of
import com.vipin.stepgreen.core.domain.repository.DayRepository

interface GetSummary {
    suspend operator fun invoke(): StatsSummary
}

class GetSummaryImpl(
    private val dayRepository: DayRepository
) : GetSummary {

    override suspend operator fun invoke(): StatsSummary {
        val allDays = dayRepository.getAllDays()
        return StatsSummary.of(allDays)
    }
}