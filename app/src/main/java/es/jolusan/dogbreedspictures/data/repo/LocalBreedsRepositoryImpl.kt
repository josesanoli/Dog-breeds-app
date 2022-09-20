package es.jolusan.dogbreedspictures.data.repo

import es.jolusan.dogbreedspictures.data.database.DogBreedsDao
import es.jolusan.dogbreedspictures.data.entities.DogBreedDBEntity
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.domain.repositories.LocalBreedsRepository
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalBreedsRepositoryImpl  @Inject constructor(
    private val localDB: DogBreedsDao
    ) : LocalBreedsRepository {
    override suspend fun getAllBreeds(): Flow<ResponseStatus<List<DogBreed>>> = flow {
        val list : List<DogBreed> = localDB.getAll().map { DogBreed(it.breedName, it.subBreedList?: emptyList()) }
        emit(ResponseStatus.Success(list))
    }

    override suspend fun insertAllBreeds(breeds: List<DogBreed>) {
        val dbList = breeds.map { DogBreedDBEntity(it.breedName, it.subBreeds) }
        localDB.insertAll(dbList)
    }

}