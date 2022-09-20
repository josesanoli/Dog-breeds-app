package es.jolusan.dogbreedspictures.presentation.pictures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jolusan.dogbreedspictures.domain.usecases.GetBreedImagesUseCase
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getBreedImagesUseCase: GetBreedImagesUseCase
): ViewModel() {

    private val _breedPictures = MutableStateFlow<ResponseStatus<List<String>>>(ResponseStatus.Loading())
    val breedPictures = _breedPictures.asStateFlow()

    fun getBreedImages(breedName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getBreedImagesUseCase(breedName).collect { responseStatus ->
                if (responseStatus is ResponseStatus.Success) {
                    _breedPictures.value = responseStatus
                } else {
                    _breedPictures.value = responseStatus
                }
            }
        }
    }
}