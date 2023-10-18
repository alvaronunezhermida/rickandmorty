package com.rickandmorty.app.di

import android.app.Application
import androidx.room.Room
import com.rickandmorty.app.data_implementation.local.room.Database
import com.rickandmorty.app.data_implementation.local.room.RoomDataSource
import com.rickandmorty.app.data_implementation.remote.Api
import com.rickandmorty.app.data_implementation.remote.clients.Client
import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.data.source.LocalDataSource
import com.rickandmorty.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideAppNavigator(): AppNavigator {
        return AppNavigator()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.inMemoryDatabaseBuilder(
        app,
        Database::class.java
    ).build()

    @Provides
    @Singleton
    fun provideBreedDao(db: Database) = db.characterDao()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "http://localhost:8080"

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
            .create()
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