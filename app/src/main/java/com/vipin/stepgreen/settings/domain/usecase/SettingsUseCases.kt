package com.vipin.stepgreen.settings.domain.usecase

import com.vipin.stepgreen.core.domain.repository.DayRepository
import com.vipin.stepgreen.settings.domain.repository.SettingsRepository

class SettingsUseCases(
    settingsRepository: SettingsRepository,
    dayRepository: DayRepository,
) {

    val getSettings: GetSettings = GetSettingsImpl(settingsRepository)
    val updateDaySettings: UpdateDaySettings = UpdateDaySettingsImpl(dayRepository)
}