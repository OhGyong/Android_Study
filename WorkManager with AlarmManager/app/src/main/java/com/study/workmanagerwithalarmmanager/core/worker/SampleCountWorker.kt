package com.study.workmanagerwithalarmmanager.core.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.study.workmanagerwithalarmmanager.domain.IncreaseSampleCountUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SampleCountWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val increaseSampleCountUseCase: IncreaseSampleCountUseCase
) : CoroutineWorker(appContext = appContext, params = params) {
    override suspend fun doWork(): Result {
        try {
            Log.d("LOG_TAG", "SampleCountWorker On")
            increaseSampleCountUseCase()
        } catch (e: Exception) {
            Log.e("SampleCountWorker", "Error increasing sample count", e)
        }

        return Result.success()
    }
}