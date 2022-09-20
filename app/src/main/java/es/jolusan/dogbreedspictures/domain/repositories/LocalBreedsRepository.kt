package es.jolusan.dogbreedspictures.domain.repositories

import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface LocalBreedsRepository {
    suspend fun getAllBreeds(): Flow<ResponseStatus<List<DogBreed>>>
    suspend fun insertAllBreeds(breeds: List<DogBreed>)
}