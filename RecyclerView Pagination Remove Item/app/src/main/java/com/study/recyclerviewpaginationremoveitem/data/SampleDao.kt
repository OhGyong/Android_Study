package com.study.recyclerviewpaginationremoveitem.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SampleDao {
    /**
     * 리스트 사이즈 호출
     */
    @Query("SELECT COUNT(*) FROM sample")
    fun getListSize(): Int

    /**
     * 아이템 10개씩 호출
     */
    @Query("SELECT * FROM sample ORDER BY id DESC LIMIT 10 OFFSET (:page-1)*10")
    fun getList(page:Int): Int

    /**
     * 아이템 삭제
     */
    @Query("DELETE FROM sample WHERE id = :id")
    fun deleteData(id: Int)
}