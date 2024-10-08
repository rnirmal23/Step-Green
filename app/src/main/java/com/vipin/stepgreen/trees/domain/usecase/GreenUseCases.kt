package com.vipin.stepgreen.trees.domain.usecase

import com.vipin.stepgreen.core.domain.repository.DayRepository

class GreenUseCases(
    dayRepository: DayRepository
) {

    val getTreeCount: GetTreeCount = GetTreeCountImpl(dayRepository)
}