package com.vipin.stepgreen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.vipin.stepgreen.GreenApplication
import com.vipin.stepgreen.core.data.repository.DayRepositoryImpl
import com.vipin.stepgreen.core.domain.model.DaySettings
import com.vipin.stepgreen.settings.data.repository.SettingsRepositoryImpl
import com.vipin.stepgreen.settings.domain.usecase.SettingsUseCases
import java.time.LocalDate

class SettingsViewModel(
    private val settingsUseCases: SettingsUseCases
) : ViewModel() {

    private var observeSettingsChangesJob: Job? = null

    fun observeSettingsChanges() {
        observeSettingsChangesJob?.cancel()
        observeSettingsChangesJob = settingsUseCases.getSettings().onEach {
            settingsUseCases.updateDaySettings(
                DaySettings(
                    date = LocalDate.now(),
                    goal = it.dailyGoal,
                    height = it.height,
                    weight = it.weight,
                    stepLength = it.stepLength,
                    pace = it.pace
                )
            )
        }.launchIn(viewModelScope)
    }

    companion object Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application = checkNotNull(extras[APPLICATION_KEY]) as GreenApplication

            val settingsStore = application.settingsStore
            val settingsRepository = SettingsRepositoryImpl(settingsStore)
            val dayDatabase = application.stepGreenDatabase
            val dayRepository = DayRepositoryImpl(dayDatabase.dayDao)

            val settingsUseCases = SettingsUseCases(settingsRepository, dayRepository)

            return SettingsViewModel(settingsUseCases) as T
        }
    }
}