package com.vipin.stepgreen.core.domain.usecase

import com.vipin.stepgreen.core.domain.repository.DayRepository
import com.vipin.stepgreen.settings.domain.repository.SettingsRepository

class DayUseCases(
    dayRepository: DayRepository,
    settingsRepository: SettingsRepository
) {

    val getDay: GetDay = GetDayImpl(dayRepository, settingsRepository)
    val incrementStepCount: IncrementStepCount = IncrementStepCountImpl(dayRepository, getDay)
}