package com.vipin.stepgreen.settings.domain.usecase

import com.vipin.stepgreen.core.domain.model.DaySettings
import com.vipin.stepgreen.core.domain.repository.DayRepository

interface UpdateDaySettings {

    suspend operator fun invoke(daySettings: DaySettings)
}

class UpdateDaySettingsImpl(
    private val dayRepository: DayRepository
) : UpdateDaySettings {

    override suspend fun invoke(daySettings: DaySettings) {
        dayRepository.updateDaySettings(daySettings)
    }
}