package com.study.paging3.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.study.paging3.data.SampleDao
import com.study.paging3.data.SampleData

class SamplePagingSource(private val dao: SampleDao): PagingSource<Int, SampleData>() {
    /**
     * 스크롤 할 때마다 데이터를 비동기적으로 가져옴
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SampleData> {
        TODO("Not yet implemented")
    }

    /**
     * 현재 목록을 대체할 새 데이터를 로드할 때 사용
     */
    override fun getRefreshKey(state: PagingState<Int, SampleData>): Int? {
        TODO("Not yet implemented")
    }
}