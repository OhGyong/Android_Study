package com.study.workmanagerwithalarmmanager.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDao {
    @Query("SELECT count FROM sample")
    fun getCount(): Flow<Int>

    @Query("UPDATE sample SET count = count + 1")
    suspend fun incrementCount()
}