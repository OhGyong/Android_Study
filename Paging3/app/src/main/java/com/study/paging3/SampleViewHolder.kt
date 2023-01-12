package com.study.paging3

import androidx.recyclerview.widget.RecyclerView
import com.study.paging3.data.SampleData
import com.study.paging3.databinding.ListItemMainBinding

class SampleViewHolder(private val mBinding : ListItemMainBinding): RecyclerView.ViewHolder(mBinding.root) {
    fun bind(listData: SampleData) {
        println("listData 어댑터 $listData")
        mBinding.tvItem.text = listData.title
    }
}