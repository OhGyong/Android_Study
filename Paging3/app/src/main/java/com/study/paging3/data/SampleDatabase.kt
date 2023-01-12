package com.study.paging3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SampleData::class], version = 1)
abstract class SampleDatabase: RoomDatabase() {
    abstract fun getSampleDao() : SampleDao

    companion object {
        @Volatile
        var sampleDB: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase =
            sampleDB ?: synchronized(this) {
                sampleDB
                    ?: buildDatabase(context).also { sampleDB = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SampleDatabase::class.java, "sample_database"
            ).build()
    }
}