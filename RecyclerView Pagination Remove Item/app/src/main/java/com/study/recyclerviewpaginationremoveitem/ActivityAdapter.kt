package com.study.recyclerviewpaginationremoveitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.recyclerviewpaginationremoveitem.databinding.ListItemMainBinding

class ActivityAdapter: RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    private val mList = ArrayList<String>()


    inner class ViewHolder(private val mBinding : ListItemMainBinding): RecyclerView.ViewHolder(mBinding.root) {
        fun bind(listData: String) {
            mBinding.tvItem.text = listData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(notifyList: ArrayList<String>) {
        mList.addAll(notifyList)
        notifyItemRangeChanged(0, mList.size)
    }
}