package com.rickandmorty.app.di

import android.app.Application
import androidx.room.Room
import com.rickandmorty.app.data_implementation.local.Database
import com.rickandmorty.app.data_implementation.local.RoomDataSource
import com.rickandmorty.app.data_implementation.remote.Api
import com.rickandmorty.app.data_implementation.remote.clients.Client
import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.data.source.LocalDataSource
import com.rickandmorty.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides dependencies for various components of the application.
 *
 * This module is responsible for defining and providing singleton-scoped dependencies used within the application.
 * These dependencies include services, data sources, network clients, and more.
 *
 * @see Module
 * @see InstallIn
 * @see SingletonComponent
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppNavigator(): AppNavigator {
        return AppNavigator()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        Database::class.java,
        "rickandmorty-db"
    ).build()

    @Provides
    @Singleton
    fun provideBreedDao(db: Database) = db.breedDao()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideBreedsApi(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideBreedsLocalDataSource(breedsRoomDataSource: RoomDataSource): LocalDataSource =
        breedsRoomDataSource

    @Provides
    @Singleton
    fun provideBreedsRemoteDataSource(breedsClient: Client): RemoteDataSource =
        breedsClient
}