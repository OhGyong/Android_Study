package com.study.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.study.datastore.data.SettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class MainApplication: Application() {
    @Module
    @InstallIn(SingletonComponent::class)
    object DataModule {
        @Singleton
        @Provides
        fun provideDataStoreRepository(@ApplicationContext context: Context)= SettingRepository(context)
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref_data")
    }
}