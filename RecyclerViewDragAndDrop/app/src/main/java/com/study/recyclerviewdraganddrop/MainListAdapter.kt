package com.study.recyclerviewdraganddrop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.recyclerviewdraganddrop.databinding.ListItemRecyclerviewBinding
import java.util.*
import kotlin.collections.ArrayList

// Activity에서 사용할 interface
interface ItemStartDragListener {
    // Drop이 됐을 때 Activity에서 사용할 메서드
    fun onDropActivity(initList : ArrayList<SampleData>, changeList: ArrayList<SampleData>)
}

class MainListAdapter :
        RecyclerView.Adapter<MainListAdapter.ViewHolder>(),
        ItemMoveListener {

    private var mSampleList: ArrayList<SampleData> = ArrayList()
    private  var onItemDragListener: ItemStartDragListener? = null
    var initList: ArrayList<SampleData> = ArrayList()

    // Activity에서 호출할 메서드
    fun itemDragListener(itf: ItemStartDragListener) {
        this.onItemDragListener = itf
    }

    inner class ViewHolder(private val mBinding: ListItemRecyclerviewBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(mSampleData: SampleData){
            mBinding.tvTitle.text = mSampleData.title
        }
    }

    fun setData(sampleList: ArrayList<SampleData>){
        mSampleList = sampleList
        initList.clear()
        initList.addAll(sampleList)

        notifyDataSetChanged()
    }

    /**
     * Drag and Drop하여 ViewHolder가 변경될 때 호출
     */
    override fun onDropAdapter() {
        onItemDragListener?.onDropActivity(initList, mSampleList) // Activity에 전달
    }

    /**
     * Item이 바뀌면 리스트에 적용
     */
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(mSampleList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mSampleList[position])
    }

    override fun getItemCount(): Int {
        return mSampleList.size
    }
}