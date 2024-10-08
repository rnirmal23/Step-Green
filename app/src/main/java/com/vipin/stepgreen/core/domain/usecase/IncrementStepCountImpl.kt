package com.vipin.stepgreen.core.domain.usecase

import kotlinx.coroutines.flow.first
import com.vipin.stepgreen.core.domain.repository.DayRepository
import java.time.LocalDate

class IncrementStepCountImpl(
    private val repository: DayRepository,
    private val getDayUseCase: GetDay
) : IncrementStepCount {

    override suspend fun invoke(date: LocalDate, by: Int) {
        val day = getDayUseCase(date).first()
        val updatedDay = day.copy(steps = day.steps + by)
        repository.upsertDay(updatedDay)
    }
}