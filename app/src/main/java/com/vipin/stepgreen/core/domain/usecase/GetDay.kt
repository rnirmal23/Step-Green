package com.vipin.stepgreen.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.core.domain.model.Day
import java.time.LocalDate

interface GetDay {

    operator fun invoke(date: LocalDate): Flow<Day>
}