package es.jolusan.dogbreedspictures.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_breeds_database")
data class DogBreedDBEntity(
    @PrimaryKey
    @ColumnInfo(name = "breed_name") val breedName: String,
    @ColumnInfo(name = "subbreed_list") val subBreedList: List<String>?
)