package com.study.flowsample.repository

import com.study.flowsample.data.FlowEntity
import com.study.flowsample.data.FlowDao
import com.study.flowsample.data.StateFlowDao
import com.study.flowsample.data.StateFlowEntity
import com.study.flowsample.data.SampleResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(
    private val flowDao: FlowDao,
    private val stateFlowDao: StateFlowDao
) {

    /**
     * Flow
     */
    fun selectFlow() : Flow<List<String>> = flowDao.selectFlow()

    fun insertFlow(flowEntity: FlowEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            flowDao.insertFlow(flowEntity)
        }
    }

    fun deleteFlow(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            flowDao.deleteFlow(data)
        }
    }

    suspend fun updateFlow(originData: String, changeData: String) : SampleResult {
        val sampleResult = SampleResult()
        try {
            sampleResult.success = flowDao.updateFlow(originData, changeData)
        } catch (e: Exception) {
            sampleResult.failure = e
        }
        return sampleResult
    }

    /**
     * State Flow
     */
    fun selectStateFlow() : Flow<List<String>> = stateFlowDao.selectStateFlow()

    fun insertStateFlow(stateFlowEntity: StateFlowEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            stateFlowDao.insertStateFlow(stateFlowEntity)
        }
    }

    fun deleteStateFlow(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            stateFlowDao.deleteStateFlow(data)
        }
    }

    suspend fun updateStateFlow(originData: String, changeData: String) : SampleResult {
        val sampleResult = SampleResult()
        try {
            sampleResult.success = stateFlowDao.updateStateFlow(originData, changeData)
        } catch (e: Exception) {
            sampleResult.failure = e
        }
        return sampleResult
    }
}