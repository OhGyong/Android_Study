package com.study.recyclerviewpaginationremoveitem.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.study.recyclerviewpaginationremoveitem.data.SampleDao
import com.study.recyclerviewpaginationremoveitem.data.SampleData
import kotlinx.coroutines.flow.Flow

class SampleRepository {
    fun getSampleData(sampleDao: SampleDao): Flow<PagingData<SampleData>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SamplePagingSource(sampleDao) }
        ).flow
    }
}