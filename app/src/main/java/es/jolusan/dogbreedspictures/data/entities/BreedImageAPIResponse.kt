package es.jolusan.dogbreedspictures.data.entities

import com.google.gson.annotations.SerializedName
import es.jolusan.dogbreedspictures.domain.model.DogBreed

data class BreedImageAPIResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)

