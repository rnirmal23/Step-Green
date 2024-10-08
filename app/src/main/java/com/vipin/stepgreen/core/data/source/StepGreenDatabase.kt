package com.vipin.stepgreen.core.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vipin.stepgreen.core.data.source.util.Converters
import com.vipin.stepgreen.core.domain.model.Day

@Database(entities = [Day::class], version = 1)
@TypeConverters(Converters::class)
abstract class StepGreenDatabase : RoomDatabase() {

    abstract val dayDao: DayDao

    companion object {
        const val DATABASE_NAME = "forest_database"
    }
}