package com.study.workmanagerwithalarmmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample")
data class SampleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    ,val count: Int = 0
)
