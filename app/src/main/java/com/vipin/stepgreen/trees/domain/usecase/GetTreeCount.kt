package com.vipin.stepgreen.trees.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.core.domain.repository.DayRepository

interface GetTreeCount {
    operator fun invoke(): Flow<Int>
}

class GetTreeCountImpl(
    private val dayRepository: DayRepository
) : GetTreeCount {

    override fun invoke(): Flow<Int> {
        return dayRepository.getTreeCount()
    }
}