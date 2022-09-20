package es.jolusan.dogbreedspictures.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.domain.usecases.GetBreedRandomImageUseCase
import es.jolusan.dogbreedspictures.domain.usecases.GetBreedsUseCase
import es.jolusan.dogbreedspictures.domain.usecases.GetLocalBreedsUseCase
import es.jolusan.dogbreedspictures.domain.usecases.InsertLocalBreedsUseCase
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetBreedsUseCase,
    private val getBreedRandomImageUseCase: GetBreedRandomImageUseCase,
    private val insertLocalBreedsUseCase: InsertLocalBreedsUseCase,
    private val getLocalBreedsUseCase: GetLocalBreedsUseCase
): ViewModel() {

    private val _dogBreed = MutableStateFlow<ResponseStatus<List<DogBreed>>>(ResponseStatus.Loading())
    val dogBreed = _dogBreed.asStateFlow()

    fun getDogBreeds() {
        if (_dogBreed.value.data.isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                getDogBreedsUseCase().collect { responseStatus ->
                    when (responseStatus) {
                        is ResponseStatus.Success -> {
                            responseStatus.data?.let { data ->
                                insertLocalBreedsUseCase(data)
                                data.forEach { breed ->
                                    getBreedRandomImageUseCase(breed.breedName).collect { responseImage ->
                                        breed.imageURL = responseImage.data ?: ""
                                    }
                                }
                                _dogBreed.value = responseStatus
                            } ?: getLocalBreeds()
                        }
                        is ResponseStatus.Error -> {
                            getLocalBreeds()
                        }
                        else -> {
                            _dogBreed.value = responseStatus
                        }
                    }
                }
            }
        }
    }

    private fun getLocalBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocalBreedsUseCase().collect { responseStatus ->
                _dogBreed.value = responseStatus
            }
        }
    }
}