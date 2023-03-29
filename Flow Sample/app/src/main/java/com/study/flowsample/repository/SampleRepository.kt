package com.study.flowsample.repository

import com.study.flowsample.data.Sample
import com.study.flowsample.data.SampleDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(private val sampleDao: SampleDao) {
    fun callSelect() : Flow<List<String>> {
        return sampleDao.selectAll()
    }

    fun callInsert(sample: Sample) {
        CoroutineScope(Dispatchers.IO).launch {
            sampleDao.insertSample(sample)
        }
    }

    fun callDelete(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            sampleDao.deleteSample(name)
        }
    }

    fun callUpdate(originName: String, changeName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            sampleDao.updateSample(originName, changeName)
        }
    }
}