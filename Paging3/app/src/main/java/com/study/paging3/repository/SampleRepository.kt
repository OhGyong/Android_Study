package com.study.paging3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.study.paging3.data.SampleData
import kotlinx.coroutines.flow.Flow

class SampleRepository {
    /**
     * Pager를 사용하여 PagingData를 반환
     * • PagingConfig로 페이지 동작을 결정(페이지 크기)
     */
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