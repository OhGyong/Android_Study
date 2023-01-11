package com.study.paging3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.study.paging3.data.SampleDao
import com.study.paging3.data.SampleData
import kotlinx.coroutines.flow.Flow

class SampleRepository {
    fun getSampleData(sampleDao: SampleDao): Flow<PagingData<SampleData>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SamplePagingSource(sampleDao) }
        ).flow
    }
}