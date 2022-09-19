package es.jolusan.dogbreedspictures.presentation.pictures

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jolusan.dogbreedspictures.domain.usecases.GetBreedsUseCase
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetBreedsUseCase
): ViewModel() {

    fun getBreedPictures(breedName: String) {

    }
}