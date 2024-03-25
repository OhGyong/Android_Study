package com.study.flowsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flow_db")
data class FlowEntity(
    @PrimaryKey @ColumnInfo(name = "data") val data: String
)

@Entity(tableName = "state_flow_db")
data class StateFlowEntity(
    @PrimaryKey @ColumnInfo(name = "data") val data: String
)
