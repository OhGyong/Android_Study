package com.study.workmanagerwithalarmmanager.core.worker

import androidx.work.WorkerFactory
import com.study.workmanagerwithalarmmanager.domain.IncreaseSampleCountUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class WorkerModule {
    @Provides
    fun provideWorkerFactory(
        increaseSampleCountUseCase: IncreaseSampleCountUseCase
    ): WorkerFactory = SampleCountWorkerFactory(increaseSampleCountUseCase)
}