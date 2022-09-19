package es.jolusan.dogbreedspictures.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    fun onBreedClicked(breed: DogBreed) {

    }
}