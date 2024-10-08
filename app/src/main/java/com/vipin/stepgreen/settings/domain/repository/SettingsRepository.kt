package com.vipin.stepgreen.settings.domain.repository

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.settings.domain.model.Settings

interface SettingsRepository {

    fun getSettings(): Flow<Settings>
}