package com.study.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.study.paging3.data.SampleData
import com.study.paging3.databinding.ListItemMainBinding

class ActivityAdapter: PagingDataAdapter<SampleData, SampleViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder =
        SampleViewHolder(
            ListItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<SampleData>() {
            override fun areItemsTheSame(oldItem: SampleData, newItem: SampleData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SampleData, newItem: SampleData): Boolean =
                oldItem == newItem
        }
    }
}