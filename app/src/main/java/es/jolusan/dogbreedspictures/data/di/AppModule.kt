package es.jolusan.dogbreedspictures.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.jolusan.dogbreedspictures.BuildConfig
import es.jolusan.dogbreedspictures.app.AppDatabase
import es.jolusan.dogbreedspictures.data.apiservice.DogAPI
import es.jolusan.dogbreedspictures.data.database.DogBreedsDao
import es.jolusan.dogbreedspictures.data.repo.DogBreedsRepositoryImpl
import es.jolusan.dogbreedspictures.data.repo.LocalBreedsRepositoryImpl
import es.jolusan.dogbreedspictures.domain.repositories.DogBreedsRepository
import es.jolusan.dogbreedspictures.domain.repositories.LocalBreedsRepository
import es.jolusan.dogbreedspictures.utils.Constants
import es.jolusan.dogbreedspictures.utils.Converter
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val converter: Converter = Converter()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            addInterceptor { chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .build()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder
                    .build()
                chain.proceed(request)
            }
        }.build()
    }

    @Provides
    @Singleton
    fun provideDogBreedsRepository(api: DogAPI): DogBreedsRepository {
        return DogBreedsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDogBreedsApi(okHttpClient: OkHttpClient): DogAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(DogAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalBreedsRepository(localDB: DogBreedsDao): LocalBreedsRepository {
        return LocalBreedsRepositoryImpl(localDB)
    }

    @Provides
    fun provideDogBreedsDao(appDatabase: AppDatabase): DogBreedsDao {
        return appDatabase.dogBreedsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "dog_breeds_database"
        )
            .addTypeConverter(converter)
            .build()
    }

}
