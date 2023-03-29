package com.study.flowsample.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Sample::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}