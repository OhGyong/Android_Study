package com.study.workmanagerwithalarmmanager

import android.app.Application
import androidx.work.Configuration
import com.study.workmanagerwithalarmmanager.core.worker.WorkerFactoryEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application(), Configuration.Provider {
    override val workManagerConfiguration: Configuration
        get() {
            val factory = EntryPointAccessors.fromApplication(this, WorkerFactoryEntryPoint::class.java)
                .getWorkerFactory()
            return Configuration.Builder().setWorkerFactory(factory).build()
        }
}