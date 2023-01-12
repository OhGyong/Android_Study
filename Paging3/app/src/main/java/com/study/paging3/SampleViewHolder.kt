package com.study.paging3

import androidx.recyclerview.widget.RecyclerView
import com.study.paging3.data.SampleData
import com.study.paging3.databinding.ListItemMainBinding

class SampleViewHolder(private val mBinding : ListItemMainBinding): RecyclerView.ViewHolder(mBinding.root) {
    fun bind(listData: SampleData) {
        mBinding.tvItem.text = listData.title
    }
}