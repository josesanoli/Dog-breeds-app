package es.jolusan.dogbreedspictures.data.repo

import com.bumptech.glide.load.HttpException
import es.jolusan.dogbreedspictures.R
import es.jolusan.dogbreedspictures.data.apiservice.DogAPI
import es.jolusan.dogbreedspictures.data.entities.toDogBreedsList
import es.jolusan.dogbreedspictures.domain.model.DogBreed
import es.jolusan.dogbreedspictures.domain.repositories.DogBreedsRepository
import es.jolusan.dogbreedspictures.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class DogBreedsRepositoryImpl  @Inject constructor(private val api: DogAPI) : DogBreedsRepository {
    override suspend fun getDogBreeds(): Flow<ResponseStatus<List<DogBreed>>> = flow {
        try {
            emit(ResponseStatus.Loading())
            val list = api.getAllBreedsList().toDogBreedsList()
            emit(ResponseStatus.Success(list))
        } catch (e: HttpException) {
            emit(ResponseStatus.Error((R.string.error_response)))
        } catch (e: IOException) {
            emit(ResponseStatus.Error((R.string.error_response)))
        }
    }
}