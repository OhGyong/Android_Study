package com.study.flowsample.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FlowEntity::class], version = 1, exportSchema = false)
abstract class FlowDatabase : RoomDatabase() {
    abstract fun flowDao(): FlowDao
}

@Database(entities = [StateFlowEntity::class], version = 1, exportSchema = false)
abstract class StateFlowDatabase : RoomDatabase() {
    abstract fun stateFlowDao(): StateFlowDao
}