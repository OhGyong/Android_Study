package com.study.recyclerviewpaginationremoveitem.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SampleData::class], version = 1)
abstract class SampleDatabase: RoomDatabase() {
    abstract fun getSampleDao() : SampleDao
}