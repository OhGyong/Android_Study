package com.study.flowsample.di

import android.content.Context
import androidx.room.Room
import com.study.flowsample.data.SampleDao
import com.study.flowsample.data.SampleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDao(sampleDatabase: SampleDatabase) : SampleDao {
        return sampleDatabase.sampleDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            SampleDatabase::class.java, "sample_db"
        ).build()
}