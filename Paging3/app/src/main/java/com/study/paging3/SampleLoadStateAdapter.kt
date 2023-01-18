package com.study.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.study.paging3.databinding.ListItemFooterBinding

/**
 * Paging3 라이브러리에서 제공하는 로드 상태를 표시하는 용도의 Adapter`
 * - 로드 상태가 변경되면 LoadState 값을 자동으로 알림을 받음
 */
class SampleLoadStateAdapter : LoadStateAdapter<SampleLoadStateAdapter.SampleLoadStateViewHolder>() {

    /**
     * LoadState 값을 받아 로딩 상태에 따라 ProgressBar의 visible 설정 처리
     */
    inner class SampleLoadStateViewHolder(
        private val binding: ListItemFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.pbItemLoading.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: SampleLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): SampleLoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_footer, parent, false)
        val binding = ListItemFooterBinding.bind(view)
        return SampleLoadStateViewHolder(binding)
    }
}