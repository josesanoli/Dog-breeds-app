package es.jolusan.dogbreedspictures.data.apiservice

import es.jolusan.dogbreedspictures.data.entities.BreedImageAPIResponse
import es.jolusan.dogbreedspictures.data.entities.DogAPIResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {
    @GET("breeds/list/all")
    suspend fun getAllBreedsList(
    ): DogAPIResponse

    @GET("breed/{breedName}/images/random")
    suspend fun getBreedImage(
        @Path("breedName") breedName: String
    ): BreedImageAPIResponse
}