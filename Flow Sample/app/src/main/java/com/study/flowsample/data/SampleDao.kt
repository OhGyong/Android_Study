package com.study.flowsample.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDao {
    @Query("SELECT * FROM sample_db")
    fun selectAll(): Flow<List<String>>
}