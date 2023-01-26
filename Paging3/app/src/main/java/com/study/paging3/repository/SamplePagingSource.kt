package com.study.paging3.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.study.paging3.data.SampleData
import com.study.paging3.data.SampleDatabase
import kotlinx.coroutines.*
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
        delay(2000)
        return try {
            val data: List<SampleData> = SampleDatabase.sampleDB!!.getSampleDao().getList(page)

            println("page : $page")
            println("loadSize: ${params.loadSize}")
            println(data)
            println("------")

            // 반환할 데이터
            LoadResult.Page(
                data = data!!,
                prevKey = if (page == STARTING_PAGE) null else page-1,
                nextKey = if(data.isEmpty()) null else page+1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * 현재 목록을 대체할 새 데이터를 로드할 때 사용
     * - anchorPosition : 가장 최근에 액세스한 인덱스
     * - closestPageToPosition : anchorPosition을 토대로 가장 가까운 페이지를 다시 호출
     */
    override fun getRefreshKey(state: PagingState<Int, SampleData>): Int? {
        println("getRefreshKey $state")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

private const val STARTING_PAGE = 1 // 초기 페이지 상수 값
