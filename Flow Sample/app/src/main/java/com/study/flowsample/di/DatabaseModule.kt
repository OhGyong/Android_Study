package com.study.flowsample.di

import android.content.Context
import androidx.room.Room
import com.study.flowsample.data.ColdDao
import com.study.flowsample.data.ColdDatabase
import com.study.flowsample.data.HotDao
import com.study.flowsample.data.HotDatabase
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
    fun provideColdDao(coldDatabase: ColdDatabase) : ColdDao {
        return coldDatabase.coldDao()
    }

    @Provides
    @Singleton
    fun provideColdDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ColdDatabase::class.java, "cold_db"
        ).build()

    @Provides
    fun provideHotDao(hotDatabase: HotDatabase) : HotDao {
        return hotDatabase.hotDao()
    }

    @Provides
    @Singleton
    fun provideHotDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            HotDatabase::class.java, "hot_db"
        ).build()
}