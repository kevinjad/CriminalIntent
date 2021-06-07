package io.kevinjad.criminalintent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class CrimeDetailsViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    private var crimeIdLiveData = MutableLiveData<UUID>()

    var crimeLiveData = Transformations.switchMap(crimeIdLiveData){ crimeId -> crimeRepository.getCrime(crimeId)}

    fun loadCrime(crimeId: UUID){
        crimeIdLiveData.value = crimeId
    }
}