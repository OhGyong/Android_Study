package com.study.workmanagerwithalarmmanager.core.worker

import androidx.work.WorkerFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WorkerFactoryEntryPoint {
    fun getWorkerFactory(): WorkerFactory
}