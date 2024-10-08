package com.vipin.stepgreen.settings.data.source

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.settings.domain.model.Settings

interface SettingsStore {

    fun getSettings(): Flow<Settings>
}