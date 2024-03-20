package com.study.flowsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cold_db")
data class ColdEntity(
    @PrimaryKey @ColumnInfo(name = "data") val data: String
)

@Entity(tableName = "hot_db")
data class HotEntity(
    @PrimaryKey @ColumnInfo(name = "data") val data: String
)
