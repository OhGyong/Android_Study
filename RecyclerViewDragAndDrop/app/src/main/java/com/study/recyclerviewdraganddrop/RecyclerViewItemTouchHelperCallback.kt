package com.study.recyclerviewdraganddrop

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

// 리스너를 위한 인터페이스
interface ItemMoveListener {
    // Drag 처리를 위한 함수
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onDragEnd()
}

/**
 * ItemTouchHelper는 RecyclerView에 Swipe와 Drag and Drop을 지원하는 유틸리티 클래스이다.
 */
class RecyclerViewItemTouchHelperCallback(private val moveListener: ItemMoveListener) : ItemTouchHelper.Callback() {

    /**
     *  동작방식 구현(동작방향)
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    /**
     * Item이 위 아래로 움직일 때의 동작 구현
     * - ItemTouchHelper가 드래그된 항목을 이전 위치에서 새 위치로 이동하려고 할 때 호출
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        moveListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    /**
     * Item이 옆으로 움직일 때의 동작 구현
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }

    /**
     * ItemTouchHelper로 Swipe 또는 Drag and Drop하여 ViewHolder가 변경될 때 호출
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when(actionState){
            ItemTouchHelper.ACTION_STATE_IDLE -> moveListener.onDragEnd()
        }
    }
}

