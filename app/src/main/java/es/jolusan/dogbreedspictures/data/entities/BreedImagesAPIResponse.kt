package es.jolusan.dogbreedspictures.data.entities

import com.google.gson.annotations.SerializedName

data class BreedImagesAPIResponse(
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String
)

