package com.study.recyclerviewpaginationremoveitem.data

import androidx.room.Database

@Database(entities = [SampleData::class], version = 1)
abstract class SampleDatabase {
    abstract fun gridViewDao() : SampleDao
}