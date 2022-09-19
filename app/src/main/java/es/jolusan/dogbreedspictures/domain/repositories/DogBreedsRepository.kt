package es.jolusan.dogbreedspictures.domain.repositories

import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface DogBreedsRepository {
    suspend fun getDogBreeds(): Flow<ResponseStatus<List<DogBreed>>>
    suspend fun getBreedRandomImage(breedName: String): Flow<ResponseStatus<String>>
    suspend fun getBreedImages(breedName: String): Flow<ResponseStatus<List<String>>>
}