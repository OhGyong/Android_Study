package com.study.recyclerviewdraganddrop

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemTouchHelperCallback(private val moveListener: ItemMoveListener) : ItemTouchHelper.Callback() {

    // 리스너를 위한 인터페이스
    interface ItemMoveListener {
        // Drag 처리를 위한 함수
        fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
        fun onDragEnd()
    }

    /**
     *  동작방식 구현
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

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when(actionState){
            ItemTouchHelper.ACTION_STATE_IDLE -> moveListener.onDragEnd()
        }
    }
}

