package es.jolusan.dogbreedspictures.domain.model

data class DogBreed(
    val breedName: String = "",
    val subBreeds: List<String> = emptyList(),
    var imageURL: String = ""
)
