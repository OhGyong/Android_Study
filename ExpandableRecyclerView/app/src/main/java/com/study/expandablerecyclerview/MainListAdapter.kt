package com.study.expandablerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.expandablerecyclerview.databinding.ListItemRecyclerviewBinding

class MainListAdapter(private var sampleDataList: ArrayList<SampleData>) :
    RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mSampleData: SampleData){
            binding.tvTitle.text = mSampleData.title
            binding.tvDate.text = mSampleData.date
            binding.tvContent.text = mSampleData.content

            binding.ctParent.setOnClickListener {
                if(mSampleData.expandable){
                    binding.ctChild.visibility = View.GONE
                    mSampleData.expandable = false
                }else{
                    binding.ctChild.visibility = View.VISIBLE
                    mSampleData.expandable = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sampleDataList[position])
    }

    override fun getItemCount(): Int {
        return sampleDataList.size
    }
}