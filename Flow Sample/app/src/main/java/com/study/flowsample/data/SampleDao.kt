package com.study.flowsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDao {
    @Query("SELECT * FROM sample_db")
    fun selectAll(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSample(name: Sample)

    @Query("DELETE FROM sample_db WHERE name=:name")
    fun deleteSample(name: String)

    @Query("UPDATE sample_db SET name=:changeName WHERE name=:originName")
    fun updateSample(originName: String, changeName: String)

    //////////////////

    @Query("SELECT * FROM sample_db")
    fun selectAll2(): List<String>
}