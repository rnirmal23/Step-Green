package com.vipin.stepgreen.settings.data.repository

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.settings.data.source.SettingsStore
import com.vipin.stepgreen.settings.domain.model.Settings
import com.vipin.stepgreen.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsStore: SettingsStore
) : SettingsRepository {

    override fun getSettings(): Flow<Settings> {
        return settingsStore.getSettings()
    }
}