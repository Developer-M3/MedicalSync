package com.mthree.medicalsync.di

import android.app.Application
import androidx.room.Room
import com.mthree.medicalsync.data.ControlDatabase
import com.mthree.medicalsync.data.repository.ControlRepositoryImpl
import com.mthree.medicalsync.domain.repository.ControlRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ControlDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideControlDatabase(app: Application): ControlDatabase {
        return Room.databaseBuilder(
            app,
            ControlDatabase::class.java,
            "control_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideControlRepository(
        db: ControlDatabase
    ): ControlRepository {
        return ControlRepositoryImpl(
            dao = db.dao
        )
    }
}
