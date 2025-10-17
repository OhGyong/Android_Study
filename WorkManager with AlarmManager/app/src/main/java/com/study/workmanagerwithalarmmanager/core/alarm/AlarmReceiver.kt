package com.study.workmanagerwithalarmmanager.core.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.study.workmanagerwithalarmmanager.core.worker.SampleCountWorker

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("LOG_TAG", "AlarmReceiver On")

        val workManager = WorkManager.getInstance(context.applicationContext)
        val workerRequest = OneTimeWorkRequestBuilder<SampleCountWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()

        workManager.enqueueUniqueWork(
            "SampleCountWorkName",
            ExistingWorkPolicy.KEEP,
            workerRequest
        )
    }
}