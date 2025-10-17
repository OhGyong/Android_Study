package com.study.workmanagerwithalarmmanager.core.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.study.workmanagerwithalarmmanager.domain.IncreaseSampleCountUseCase

class SampleCountWorkerFactory(
    private val increaseSampleCountUseCase: IncreaseSampleCountUseCase
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = SampleCountWorker(
        appContext,
        workerParameters,
        increaseSampleCountUseCase
    )
}