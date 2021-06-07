package io.kevinjad.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import io.kevinjad.criminalintent.database.CrimeDao
import io.kevinjad.criminalintent.database.CrimeDatabase
import java.util.*


private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){


    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    )//.createFromAsset("databases/crime-database")
        .build()

    private val crimeDao = database.crimeDao()

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    fun updateCrime(crime: Crime) = crimeDao.updateCrime(crime)

    fun addCrime(crime: Crime) = crimeDao.addCrime(crime)

    companion object{
        var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository{
            return INSTANCE ?: throw IllegalStateException("CrimeRepository is not initialized");
        }
    }
}