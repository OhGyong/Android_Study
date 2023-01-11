package com.study.paging3.data

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "sample")
data class SampleData(
    @ColumnInfo(name = "title") val title: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}