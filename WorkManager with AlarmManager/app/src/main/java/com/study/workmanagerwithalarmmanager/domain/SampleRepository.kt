package com.study.workmanagerwithalarmmanager.domain

import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    fun get(): Flow<Int>
    suspend fun incrementCount()
    suspend fun clearCount()
}