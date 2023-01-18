package com.study.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.study.paging3.databinding.ListItemFooterBinding

class SampleLoadStateAdapter : LoadStateAdapter<SampleLoadStateAdapter.SampleLoadStateViewHolder>() {

    inner class SampleLoadStateViewHolder(
        private val binding: ListItemFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.pbLoading.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: SampleLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): SampleLoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_footer, parent, false)
        val binding = ListItemFooterBinding.bind(view)
        return SampleLoadStateViewHolder(binding)
    }
}