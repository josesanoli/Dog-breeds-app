package es.jolusan.dogbreedspictures.data.entities

import com.google.gson.annotations.SerializedName
import es.jolusan.dogbreedspictures.domain.model.DogBreed

data class BreedImagesAPIResponse(
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String
)

