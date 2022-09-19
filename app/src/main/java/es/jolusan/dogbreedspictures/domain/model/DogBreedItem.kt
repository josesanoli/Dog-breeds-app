package es.jolusan.dogbreedspictures.domain.model

data class DogBreedItem(
    val breedName: String,
    val subBreeds: String,
    val imageURL: String
)

fun DogBreed.toListItem(): DogBreedItem = DogBreedItem(
    breedName = breedName,
    subBreeds = subBreeds.joinToString(", ") { it },
    imageURL = ""
)
