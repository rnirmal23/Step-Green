package com.vipin.stepgreen

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.flow.MutableStateFlow
import com.vipin.stepgreen.core.data.source.StepGreenDatabase
import com.vipin.stepgreen.settings.data.source.SettingsStore
import com.vipin.stepgreen.settings.data.source.SettingsStoreImpl
import java.time.LocalDate

class GreenApplication : Application() {

    lateinit var settingsStore: SettingsStore
    lateinit var stepGreenDatabase: StepGreenDatabase

    val currentDate = MutableStateFlow<LocalDate>(LocalDate.now())

    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)
        PreferenceManager.setDefaultValues(this, R.xml.settings, false)
        registerMidnightTimer()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        settingsStore = SettingsStoreImpl(sharedPreferences)

        stepGreenDatabase = Room.databaseBuilder(
            applicationContext,
            StepGreenDatabase::class.java,
            StepGreenDatabase.DATABASE_NAME
        ).build()
    }

    private fun registerMidnightTimer() {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }
        registerReceiver(midnightBroadcastReceiver, intentFilter)
    }

    private val midnightBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val today = LocalDate.now()
            if (today != currentDate.value) {
                currentDate.value = today
            }
        }
    }
}