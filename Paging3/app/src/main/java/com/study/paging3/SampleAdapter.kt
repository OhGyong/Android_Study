package com.study.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.study.paging3.data.SampleData
import com.study.paging3.databinding.ListItemMainBinding

/**
 * (기본 코드 랩 8p)
 * PagingData 콘텐츠가 load 될 때마다 PagingDataAdapter에서 알림을 받고
 * RecyclerView에 업데이트 하라는 신호를 보냄
 *
 * (기타)
 * 백그라운드 스레드에서 DiffUtil을 사용하여 데이터를 정제하고
 * 데이터를 불러오기 때문에 UI가 부드럽게 나타난다.
 */
class SampleAdapter: PagingDataAdapter<SampleData, SampleViewHolder>(ARTICLE_DIFF_CALLBACK) {

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