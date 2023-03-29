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
    /**
     * select 호출
     */
    fun callSelect() : Flow<List<String>> {
        return sampleDao.selectAll()
    }

    /**
     * insert 호출
     */
    fun callInsert(sample: Sample) {
        CoroutineScope(Dispatchers.IO).launch {
            sampleDao.insertSample(sample)
        }
    }

    /**
     * delete 호출
     */
    fun callDelete(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            sampleDao.deleteSample(name)
        }
    }

    /**
     * update 호출
     */
    fun callUpdate(originName: String, changeName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            sampleDao.updateSample(originName, changeName)
        }
    }
}