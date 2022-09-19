package es.jolusan.dogbreedspictures.data.apiservice

import es.jolusan.dogbreedspictures.data.entities.DogAPIResponse
import retrofit2.http.GET

interface DogAPI {
    @GET("breeds/list/all")
    suspend fun getAllBreedsList(
    ): DogAPIResponse

}