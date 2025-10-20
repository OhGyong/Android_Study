package com.study.workmanagerwithalarmmanager.data

import javax.inject.Inject

class SampleDataSource @Inject constructor(private val sampleDao: SampleDao) {
    fun getCount() = sampleDao.getCount()
    suspend fun incrementCount() {
        sampleDao.incrementCount()
    }
    suspend fun clearCount() {
        sampleDao.clearCount()
    }
}