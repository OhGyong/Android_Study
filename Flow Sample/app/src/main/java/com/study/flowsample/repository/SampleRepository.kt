package com.study.flowsample.repository

import com.study.flowsample.data.SampleDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(private val sampleDatabase: SampleDatabase) {

    fun callSelect() : Flow<List<String>> {
        return sampleDatabase.sampleDao().selectAll()
    }
}