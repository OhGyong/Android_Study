package com.study.flowsample.di

import android.content.Context
import androidx.room.Room
import com.study.flowsample.data.FlowDao
import com.study.flowsample.data.FlowDatabase
import com.study.flowsample.data.StateFlowDao
import com.study.flowsample.data.StateFlowDatabase
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
    fun provideFlowDao(flowDatabase: FlowDatabase) : FlowDao {
        return flowDatabase.flowDao()
    }

    @Provides
    @Singleton
    fun provideFlowDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            FlowDatabase::class.java, "flow_db"
        ).build()

    @Provides
    fun provideStateFlowDao(stateFlowDatabase: StateFlowDatabase) : StateFlowDao {
        return stateFlowDatabase.stateFlowDao()
    }

    @Provides
    @Singleton
    fun provideHotDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            StateFlowDatabase::class.java, "state_flow_db"
        ).build()
}