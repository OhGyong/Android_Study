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

/**
 * 데이터 식별자와 데이터 타입을 통해 DB의 데이터를 로드하는 곳
 */
class SamplePagingSource: PagingSource<Int, SampleData>() {
    /**
     * 스크롤 할 때마다 데이터를 비동기적으로 가져오는 메서드
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SampleData> {
        // 시작 페이지
        // 처음에 null 값인 것을 고려하여 시작 값 부여
        val page = params.key ?: STARTING_PAGE

        return try {
            var data: List<SampleData>? = null

            // page 값에 따른 list 호출
            // join을 사용해서 list 값을 저장
            runBlocking {
                CoroutineScope(Dispatchers.IO).launch {
                    data = SampleDatabase.sampleDB!!.getSampleDao().getList(page)
                }
            }.join()

            // 반환할 데이터
            LoadResult.Page(
                data = data!!,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if(data.isNullOrEmpty()) null else page+1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * 현재 목록을 대체할 새 데이터를 로드할 때 사용
     */
    override fun getRefreshKey(state: PagingState<Int, SampleData>): Int? {
        //
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

private const val STARTING_PAGE = 1 // 초기 페이지 상수 값
