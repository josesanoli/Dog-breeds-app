package es.jolusan.dogbreedspictures.domain.usecases

import es.jolusan.dogbreedspictures.domain.repositories.LocalBreedsRepository
import javax.inject.Inject

class GetLocalBreedsUseCase @Inject constructor(
    private val repository: LocalBreedsRepository
) {
    suspend operator fun invoke() = repository.getAllBreeds()
}