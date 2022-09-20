package es.jolusan.dogbreedspictures.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.jolusan.dogbreedspictures.data.entities.DogBreedDBEntity

@Dao
interface DogBreedsDao {
    @Query("SELECT * FROM dog_breeds_database")
    fun getAll(): List<DogBreedDBEntity>

    @Query("SELECT * FROM dog_breeds_database WHERE breed_name LIKE :name LIMIT 1")
    fun findByName(name: String): DogBreedDBEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breeds: List<DogBreedDBEntity>)
}