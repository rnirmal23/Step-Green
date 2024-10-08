package com.vipin.stepgreen.stats.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.vipin.stepgreen.core.domain.repository.DayRepository
import java.time.LocalDate

interface GetFirstDate {

    operator fun invoke(): Flow<LocalDate>
}

class GetFirstDateImpl(
    private val dayRepository: DayRepository
) : GetFirstDate {

    override fun invoke(): Flow<LocalDate> {
        return dayRepository.getFirstDay().map { it?.date ?: LocalDate.now() }
    }
}