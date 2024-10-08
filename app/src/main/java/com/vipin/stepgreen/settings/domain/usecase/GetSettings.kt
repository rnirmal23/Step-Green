package com.vipin.stepgreen.settings.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.settings.domain.model.Settings
import com.vipin.stepgreen.settings.domain.repository.SettingsRepository

interface GetSettings {

    operator fun invoke(): Flow<Settings>
}

class GetSettingsImpl(
    private val repository: SettingsRepository
) : GetSettings {

    override fun invoke(): Flow<Settings> {
        return repository.getSettings()
    }
}