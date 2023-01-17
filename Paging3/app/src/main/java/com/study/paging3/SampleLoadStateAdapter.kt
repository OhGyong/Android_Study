package com.study.paging3

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class SampleLoadStateAdapter : LoadStateAdapter<SampleLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: SampleLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): SampleLoadStateViewHolder {
        return SampleLoadStateViewHolder.create(parent)
    }
}