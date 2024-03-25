package com.study.flowsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowDao {
    @Query("SELECT * FROM flow_db")
    fun selectFlow(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFlow(data: FlowEntity)

    @Query("DELETE FROM flow_db WHERE data=:data")
    fun deleteFlow(data: String)

    @Query("UPDATE flow_db SET data=:changeData WHERE data=:originData")
    suspend fun updateFlow(originData: String, changeData: String)
}

@Dao
interface StateFlowDao {
    @Query("SELECT * FROM state_flow_db")
    fun selectStateFlow(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStateFlow(data: StateFlowEntity)

    @Query("DELETE FROM state_flow_db WHERE data=:data")
    fun deleteStateFlow(data: String)

    @Query("UPDATE state_flow_db SET data=:changeData WHERE data=:originData")
    suspend fun updateStateFlow(originData: String, changeData: String)
}