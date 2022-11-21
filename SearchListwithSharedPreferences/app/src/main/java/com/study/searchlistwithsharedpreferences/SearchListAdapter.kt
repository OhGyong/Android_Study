package com.study.searchlistwithsharedpreferences

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.searchlistwithsharedpreferences.databinding.ItemSearchBinding

class SearchListAdapter: RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    private var itemList: ArrayList<PrefData> = arrayListOf()

    inner class ViewHolder(private val mBinding: ItemSearchBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(mItem: PrefData) {
            mBinding.tvSearchText.text = mItem.text
            mBinding.tvSearchDate.text = mItem.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(pList: ArrayList<PrefData>) {
        itemList = pList
        notifyItemRangeChanged(0, itemList.size)
    }
}