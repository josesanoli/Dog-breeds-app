package es.jolusan.dogbreedspictures.data.entities

import com.google.gson.annotations.SerializedName
import es.jolusan.dogbreedspictures.domain.model.DogBreed

data class DogAPIResponse(
    @SerializedName("message")
    val message: Map<String,List<String>>,
    @SerializedName("status")
    val status: String
)

fun DogAPIResponse.toDogBreedsList(): List<DogBreed> {
    val messageList = this.message.toList()
    val dogBreedsList: ArrayList<DogBreed> = arrayListOf()
    messageList.forEach { breed ->
        val dogBreed = DogBreed(breed.first, breed.second )
        dogBreedsList.add(dogBreed)
    }
    return dogBreedsList
}
