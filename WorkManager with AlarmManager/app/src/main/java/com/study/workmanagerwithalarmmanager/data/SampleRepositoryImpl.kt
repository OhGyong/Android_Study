package com.study.workmanagerwithalarmmanager.data

import com.study.workmanagerwithalarmmanager.domain.SampleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource
) : SampleRepository {
    override fun get(): Flow<Int> = sampleDataSource.getCount()

    override suspend fun incrementCount() {
        sampleDataSource.incrementCount()
    }

    override suspend fun clearCount() {
        sampleDataSource.clearCount()
    }
}