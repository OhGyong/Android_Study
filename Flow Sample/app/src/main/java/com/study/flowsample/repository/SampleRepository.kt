package com.study.flowsample.repository

import com.study.flowsample.data.Sample
import com.study.flowsample.data.SampleDao
import com.study.flowsample.data.SampleResult
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
    suspend fun callUpdate(originName: String, changeName: String) : SampleResult {
        val sampleResult = SampleResult()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                sampleResult.success = sampleDao.updateSample(originName, changeName)
            } catch (e: Exception) {
                sampleResult.failure = e
            }
        }.join()
        return sampleResult
    }

    //////////////////

    fun callSelect2(): List<String> {
        return sampleDao.selectAll2()
    }

    suspend fun callSelect3(): List<String> {
        var list = emptyList<String>()
        CoroutineScope(Dispatchers.IO).launch {
            list = sampleDao.selectAll2()
        }.join()
        return list
    }

    fun callSelect4(): Flow<List<String>> {
        return sampleDao.selectAll()
    }
}