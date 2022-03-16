package com.revangga.revangga_nasya_mvvm.di

import android.app.Application
import androidx.room.Room
import com.revangga.revangga_nasya_mvvm.data.local.DogsDao
import com.revangga.revangga_nasya_mvvm.data.local.DogsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): DogsDatabase {
        return Room.databaseBuilder(
            application,
            DogsDatabase::class.java,
            "dogs_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDogsDao(dogsDatabase: DogsDatabase): DogsDao {
        return dogsDatabase.dog()
    }
}