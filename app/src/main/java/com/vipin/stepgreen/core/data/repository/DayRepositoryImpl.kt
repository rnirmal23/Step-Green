package com.vipin.stepgreen.core.data.repository

import kotlinx.coroutines.flow.Flow
import com.vipin.stepgreen.core.data.source.DayDao
import com.vipin.stepgreen.core.domain.model.Day
import com.vipin.stepgreen.core.domain.model.DaySettings
import com.vipin.stepgreen.core.domain.repository.DayRepository
import java.time.LocalDate

class DayRepositoryImpl(
    private val dao: DayDao
) : DayRepository {

    override fun getTreeCount(): Flow<Int> {
        return dao.getTreeCount()
    }

    override fun getFirstDay(): Flow<Day?> {
        return dao.getFirstDay()
    }

    override fun getDay(date: LocalDate): Flow<Day?> {
        return dao.getDay(date)
    }

    override suspend fun getAllDays(): List<Day> {
        return dao.getAllDays()
    }

    override fun getDays(range: ClosedRange<LocalDate>): Flow<List<Day>> {
        return dao.getDays(range.start, range.endInclusive)
    }

    override suspend fun upsertDay(day: Day) {
        dao.upsertDay(day)
    }

    override suspend fun updateDaySettings(daySettings: DaySettings) {
        dao.updateDaySettings(daySettings)
    }
}