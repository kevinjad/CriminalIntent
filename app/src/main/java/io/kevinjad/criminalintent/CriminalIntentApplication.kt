package io.kevinjad.criminalintent

import android.app.Application
import androidx.room.Room
import io.kevinjad.criminalintent.database.CrimeDatabase

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}