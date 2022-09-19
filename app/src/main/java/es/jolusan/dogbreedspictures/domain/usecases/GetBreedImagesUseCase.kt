package es.jolusan.dogbreedspictures.domain.usecases

import es.jolusan.dogbreedspictures.domain.repositories.DogBreedsRepository
import javax.inject.Inject

class GetBreedImagesUseCase @Inject constructor(
    private val repository: DogBreedsRepository
) {
    suspend operator fun invoke(breedName: String) = repository.getBreedImages(breedName)
}