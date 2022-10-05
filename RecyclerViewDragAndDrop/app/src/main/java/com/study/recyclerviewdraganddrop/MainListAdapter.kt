package com.study.recyclerviewdraganddrop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.recyclerviewdraganddrop.databinding.ListItemRecyclerviewBinding
import java.util.*
import kotlin.collections.ArrayList

// Activity에서 사용할 interface
interface ItemStartDragListener {
    fun onDropActivity(initList : ArrayList<SampleData>, changeList: ArrayList<SampleData>)
}

class MainListAdapter(private val listener: ItemStartDragListener) :
        RecyclerView.Adapter<MainListAdapter.ViewHolder>(),
        ItemMoveListener {

    private var mSampleList: ArrayList<SampleData> = ArrayList()
    var initList: ArrayList<SampleData> = ArrayList()

    inner class ViewHolder(private val binding: ListItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mSampleData: SampleData){
            binding.tvTitle.text = mSampleData.title
        }
    }

    fun setData(sampleList: ArrayList<SampleData>){
        mSampleList = sampleList
        initList.clear()
        initList.addAll(sampleList)

        notifyDataSetChanged()
    }

    override fun onDropAdapter() {
        listener.onDropActivity(initList, mSampleList)
    }

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