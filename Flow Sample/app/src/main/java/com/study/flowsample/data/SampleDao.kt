package com.study.flowsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ColdDao {
    @Query("SELECT * FROM cold_db")
    fun selectCold(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCold(data: ColdEntity)

    @Query("DELETE FROM cold_db WHERE data=:data")
    fun deleteCold(data: String)

    @Query("UPDATE cold_db SET data=:changeData WHERE data=:originData")
    suspend fun updateCold(originData: String, changeData: String)
}

@Dao
interface HotDao {
    @Query("SELECT * FROM hot_db")
    fun selectHot(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHot(data: HotEntity)

    @Query("DELETE FROM hot_db WHERE data=:data")
    fun deleteHot(data: String)

    @Query("UPDATE hot_db SET data=:changeData WHERE data=:originData")
    suspend fun updateHot(originData: String, changeData: String)
}