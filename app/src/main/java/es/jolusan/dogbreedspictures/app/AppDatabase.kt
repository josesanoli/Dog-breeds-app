package es.jolusan.dogbreedspictures.app

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.jolusan.dogbreedspictures.data.database.DogBreedsDao
import es.jolusan.dogbreedspictures.data.entities.DogBreedDBEntity
import es.jolusan.dogbreedspictures.utils.Converter

@Database(entities = [DogBreedDBEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogBreedsDao(): DogBreedsDao
}