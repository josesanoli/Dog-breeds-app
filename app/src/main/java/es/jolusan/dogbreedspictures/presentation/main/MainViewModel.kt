package es.jolusan.dogbreedspictures.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.domain.model.DogBreedItem
import es.jolusan.dogbreedspictures.domain.usecases.GetBreedsUseCase
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetBreedsUseCase
): ViewModel() {

    private val _dogBreed = MutableStateFlow<ResponseStatus<List<DogBreed>>>(ResponseStatus.Loading())
    val dogBreed = _dogBreed.asStateFlow()

    fun getDogBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            getDogBreedsUseCase().collect {
                _dogBreed.value = it
            }
        }
    }

    fun onBreedClicked(breed: DogBreedItem) {

    }
}