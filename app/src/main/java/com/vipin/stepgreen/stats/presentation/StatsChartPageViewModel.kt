package com.vipin.stepgreen.stats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.vipin.stepgreen.GreenApplication
import com.vipin.stepgreen.core.data.repository.DayRepositoryImpl
import com.vipin.stepgreen.core.domain.model.Day
import com.vipin.stepgreen.stats.domain.usecase.StatsChartPageUseCases
import com.vipin.stepgreen.stats.util.alignWeek
import java.time.LocalDate

class StatsChartPageViewModel(
    private val statsChartPageUseCases: StatsChartPageUseCases
) : ViewModel() {

    private val _week = MutableStateFlow<List<Day>>(emptyList())
    val week: StateFlow<List<Day>> = _week.asStateFlow()

    private var getWeekJob: Job? = null
    fun selectWeek(firstDate: LocalDate) {
        getWeekJob?.cancel()
        getWeekJob = viewModelScope.launch {
            statsChartPageUseCases.getWeek(firstDate).collect { week ->
                _week.value = week.alignWeek(firstDate)
            }
        }
    }

    companion object Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as GreenApplication

            val forestDatabase = application.stepGreenDatabase
            val dayRepository = DayRepositoryImpl(forestDatabase.dayDao)
            val statsChartPageUseCases = StatsChartPageUseCases(dayRepository)

            return StatsChartPageViewModel(statsChartPageUseCases) as T
        }
    }
}