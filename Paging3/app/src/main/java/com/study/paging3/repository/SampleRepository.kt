package com.study.paging3.repository

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.study.paging3.data.SampleData
import com.study.paging3.data.SampleDatabase
import kotlinx.coroutines.flow.Flow

class SampleRepository {
    fun getSamplePagingSource(): Flow<PagingData<SampleData>> {
        return Pager(
            config =  PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SamplePagingSource()}
        ).flow
    }
}