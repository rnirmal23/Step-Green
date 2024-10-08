package com.vipin.stepgreen.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.vipin.stepgreen.GreenApplication
import com.vipin.stepgreen.core.data.repository.DayRepositoryImpl
import com.vipin.stepgreen.core.domain.usecase.DayUseCases
import com.vipin.stepgreen.settings.data.repository.SettingsRepositoryImpl
import java.time.LocalDate
import kotlin.math.roundToInt

class ProgressViewModel(
    private val dayUseCases: DayUseCases,
    private val currentDateFlow: StateFlow<LocalDate>
) : ViewModel() {

    private val _progress = MutableStateFlow(
        ProgressState(
            date = LocalDate.MIN,
            stepsTaken = 0,
            dailyGoal = 0,
            calorieBurned = 0,
            distanceTravelled = 0.0,
            carbonDioxideSaved = 0.0,
        )
    )
    val progress: StateFlow<ProgressState> = _progress.asStateFlow()

    private var getProgressJob: Job? = null

    init {
        viewModelScope.launch {
            currentDateFlow.collect { date ->
                getProgress(date)
            }
        }
    }

    private fun getProgress(date: LocalDate) {
        getProgressJob?.cancel()

        getProgressJob = dayUseCases.getDay(date).onEach { day ->
            _progress.value = progress.value.copy(
                date = day.date,
                stepsTaken = day.steps,
                dailyGoal = day.goal,
                calorieBurned = day.calorieBurned.roundToInt(),
                distanceTravelled = day.distanceTravelled,
                carbonDioxideSaved = day.carbonDioxideSaved,
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
            val dayUseCases = DayUseCases(dayRepository, settingsRepository)
            val currentDateFlow = application.currentDate

            return ProgressViewModel(dayUseCases, currentDateFlow) as T
        }
    }
}