package com.study.paging3.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SampleDao {
    /**
     * 아이템 10개씩 호출
     */
    @Query("SELECT * FROM sample ORDER BY id ASC LIMIT 10 OFFSET (:page-1)*10")
    fun getList(page:Int): List<SampleData>

    /**
     * 아이템 삭제
     */
    @Query("DELETE FROM sample WHERE id = :id")
    fun itemDelete(id: Int)
}