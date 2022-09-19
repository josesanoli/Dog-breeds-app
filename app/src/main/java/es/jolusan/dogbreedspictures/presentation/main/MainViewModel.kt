package es.jolusan.dogbreedspictures.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.domain.usecases.GetBreedImageUseCase
import es.jolusan.dogbreedspictures.domain.usecases.GetBreedsUseCase
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetBreedsUseCase,
    private val getBreedImageUseCase: GetBreedImageUseCase
): ViewModel() {

    private val _dogBreed = MutableStateFlow<ResponseStatus<List<DogBreed>>>(ResponseStatus.Loading())
    val dogBreed = _dogBreed.asStateFlow()

    fun getDogBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            getDogBreedsUseCase().collect { responseStatus ->
                if (responseStatus is ResponseStatus.Success) {
                    responseStatus.data?.forEach { breed ->
                        getBreedImageUseCase(breed.breedName).collect { responseImage ->
                            breed.imageURL = responseImage.data?:""
                        }
                    }
                    _dogBreed.value = responseStatus
                } else {
                    _dogBreed.value = responseStatus
                }
            }
        }
    }

}