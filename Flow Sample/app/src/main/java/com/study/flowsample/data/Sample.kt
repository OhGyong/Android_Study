package com.study.flowsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample_db")
data class Sample(
    @PrimaryKey @ColumnInfo(name = "name") val name: String
)