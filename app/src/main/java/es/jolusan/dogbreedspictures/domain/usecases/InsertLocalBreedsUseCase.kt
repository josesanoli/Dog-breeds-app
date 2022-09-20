package es.jolusan.dogbreedspictures.domain.usecases

import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.domain.repositories.LocalBreedsRepository
import javax.inject.Inject

class InsertLocalBreedsUseCase @Inject constructor(
    private val repository: LocalBreedsRepository
) {
    suspend operator fun invoke(breeds: List<DogBreed>) = repository.insertAllBreeds(breeds)
}