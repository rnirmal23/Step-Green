package com.vipin.stepgreen.trees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.vipin.stepgreen.GreenApplication
import com.vipin.stepgreen.core.data.repository.DayRepositoryImpl
import com.vipin.stepgreen.trees.domain.usecase.GreenUseCases

class GreenViewModel(
    greenUseCases: GreenUseCases
) : ViewModel() {

    private val _trees = MutableStateFlow(GreenState(treeCount = 0))
    val trees: StateFlow<GreenState> = _trees.asStateFlow()

    init {
        viewModelScope.launch {
            greenUseCases.getTreeCount().collect {
                _trees.value = _trees.value.copy(
                    treeCount = it
                )
            }
        }
    }

    object Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application = checkNotNull(extras[APPLICATION_KEY]) as GreenApplication
            val forestDatabase = application.stepGreenDatabase
            val dayRepository = DayRepositoryImpl(forestDatabase.dayDao)
            val greenUseCases = GreenUseCases(dayRepository)
            return GreenViewModel(greenUseCases) as T
        }
    }
}