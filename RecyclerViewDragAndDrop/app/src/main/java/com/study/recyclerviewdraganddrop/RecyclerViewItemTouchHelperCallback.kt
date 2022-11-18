package com.study.recyclerviewdraganddrop

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

// Adapter에서 사용할 interface
interface ItemMoveListener {
    // Drag 처리를 위한 메서드
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    // Drop 처리를 위한 메서드
    fun onDropAdapter()
}

/**
 * ItemTouchHelper는 RecyclerView에 Swipe와 Drag and Drop을 지원하는 유틸리티 클래스
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
        moveListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition) // Adapter에 전달
        return true
    }

    /**
     * ItemTouchHelper로 Swipe 또는 Drag and Drop하여 ViewHolder가 변경될 때 호출
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when(actionState){
            // 드래그 또는 스와이프가 끝났을 때 ACTION_STATE_IDLE가 전달 됨.
            ItemTouchHelper.ACTION_STATE_IDLE -> moveListener.onDropAdapter() // Adapter에 전달
        }
    }

    /**
     * Item이 옆으로 움직일 때의 동작 구현
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }
}

