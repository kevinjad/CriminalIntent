package io.kevinjad.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    var crimesListLiveData = crimeRepository.getCrimes()


}