package es.jolusan.dogbreedspictures.data.entities

import com.google.gson.annotations.SerializedName

data class BreedRandomImageAPIResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)

