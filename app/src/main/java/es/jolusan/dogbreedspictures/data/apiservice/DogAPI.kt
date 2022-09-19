package es.jolusan.dogbreedspictures.data.apiservice

import es.jolusan.dogbreedspictures.data.entities.BreedImagesAPIResponse
import es.jolusan.dogbreedspictures.data.entities.BreedRandomImageAPIResponse
import es.jolusan.dogbreedspictures.data.entities.DogAPIResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {
    @GET("breeds/list/all")
    suspend fun getAllBreedsList(
    ): DogAPIResponse

    @GET("breed/{breedName}/images/random")
    suspend fun getBreedRandomImage(
        @Path("breedName") breedName: String
    ): BreedRandomImageAPIResponse

    @GET("breed/{breedName}/images")
    suspend fun getBreedImages(
        @Path("breedName") breedName: String
    ): BreedImagesAPIResponse
}