package com.vipin.stepgreen.stats.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.core.domain.model.Day
import com.vipin.stepgreen.core.domain.repository.DayRepository
import java.time.LocalDate

interface GetWeek {

    operator fun invoke(startingAt: LocalDate): Flow<List<Day>>
}

class GetWeekImpl(
    private val dayRepository: DayRepository
) : GetWeek {

    override fun invoke(startingAt: LocalDate): Flow<List<Day>> {
        val endingAt = startingAt.plusDays(6)
        return dayRepository.getDays(startingAt..endingAt)
    }
}