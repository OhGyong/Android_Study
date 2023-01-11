package com.study.paging3.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.study.paging3.data.SampleDao
import com.study.paging3.data.SampleData

@Database(entities = [SampleData::class], version = 1)
abstract class SampleDatabase: RoomDatabase() {
    abstract fun getSampleDao() : SampleDao
}