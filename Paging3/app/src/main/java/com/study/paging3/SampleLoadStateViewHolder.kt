package com.study.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.study.paging3.databinding.ListItemFooterBinding

class SampleLoadStateViewHolder(
    private val binding: ListItemFooterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.pbLoading.isVisible = loadState is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup): SampleLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_footer, parent, false)
            val binding = ListItemFooterBinding.bind(view)
            return SampleLoadStateViewHolder(binding)
        }
    }
}