package com.study.flowsample.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SampleDao {
    @Query("SELECT * FROM sample_db")
    fun selectAll(): List<String>
}