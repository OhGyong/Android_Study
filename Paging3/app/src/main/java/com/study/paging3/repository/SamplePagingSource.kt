package com.study.paging3.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.study.paging3.data.SampleData
import com.study.paging3.data.SampleDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException

private const val STARTING_PAGE = 1 // 초기 페이지 상수 값

class SamplePagingSource(): PagingSource<Int, SampleData>() {
    /**
     * 스크롤 할 때마다 데이터를 비동기적으로 가져옴
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SampleData> {
        // 시작 페이지
        val position = params.key ?: STARTING_PAGE
        return try {
            // 다음 페이지
            val nextKey = position + (params.loadSize / 10)
            var data: List<SampleData>? = null
            runBlocking {
                CoroutineScope(Dispatchers.IO).launch {
                    data = SampleDatabase.sampleDB!!.getSampleDao().getList(position)
                }
            }.join()

            LoadResult.Page(
                data = data!!,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = if(data.isNullOrEmpty()) null else position+1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * 현재 목록을 대체할 새 데이터를 로드할 때 사용
     */
    override fun getRefreshKey(state: PagingState<Int, SampleData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}