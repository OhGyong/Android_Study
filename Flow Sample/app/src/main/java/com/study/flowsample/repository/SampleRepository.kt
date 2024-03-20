package com.study.flowsample.repository

import com.study.flowsample.data.ColdEntity
import com.study.flowsample.data.ColdDao
import com.study.flowsample.data.HotDao
import com.study.flowsample.data.HotEntity
import com.study.flowsample.data.SampleResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(
    private val coldDao: ColdDao,
    private val hotDao: HotDao
) {

    /**
     * Cold Flow
     */
    fun selectCold() : Flow<List<String>> {
        return coldDao.selectCold()
    }

    fun insertCold(coldEntity: ColdEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            coldDao.insertCold(coldEntity)
        }
    }

    fun deleteCold(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            coldDao.deleteCold(data)
        }
    }

    suspend fun updateCold(originData: String, changeData: String) : SampleResult {
        val sampleResult = SampleResult()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                sampleResult.success = coldDao.updateCold(originData, changeData)
            } catch (e: Exception) {
                sampleResult.failure = e
            }
        }.join()
        return sampleResult
    }

    /**
     * Hot Flow
     */
    fun selectHot() : Flow<List<String>> {
        return hotDao.selectHot()
    }

    fun insertHot(hotEntity: HotEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            hotDao.insertHot(hotEntity)
        }
    }

    fun deleteHot(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            hotDao.deleteHot(data)
        }
    }

    suspend fun updateHot(originData: String, changeData: String) : SampleResult {
        val sampleResult = SampleResult()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                sampleResult.success = hotDao.updateHot(originData, changeData)
            } catch (e: Exception) {
                sampleResult.failure = e
            }
        }.join()
        return sampleResult
    }

    //////////////////

    fun callSelect2(): List<String> {
        return coldDao.selectAll2()
    }

    suspend fun callSelect3(): List<String> {
        var list = emptyList<String>()
        CoroutineScope(Dispatchers.IO).launch {
            list = coldDao.selectAll2()
        }.join()
        return list
    }

    fun callSelect4(): Flow<List<String>> {
        return coldDao.selectCold()
    }
}