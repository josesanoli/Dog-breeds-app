package es.jolusan.dogbreedspictures.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.jolusan.dogbreedspictures.BuildConfig
import es.jolusan.dogbreedspictures.data.apiservice.DogAPI
import es.jolusan.dogbreedspictures.data.repo.DogBreedsRepositoryImpl
import es.jolusan.dogbreedspictures.domain.repositories.DogBreedsRepository
import es.jolusan.dogbreedspictures.utils.Constants
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

}
