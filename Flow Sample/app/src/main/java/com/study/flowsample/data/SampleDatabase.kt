package com.study.flowsample.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ColdEntity::class], version = 1, exportSchema = false)
abstract class ColdDatabase : RoomDatabase() {
    abstract fun coldDao(): ColdDao
}

@Database(entities = [HotEntity::class], version = 1, exportSchema = false)
abstract class HotDatabase : RoomDatabase() {
    abstract fun hotDao(): HotDao
}