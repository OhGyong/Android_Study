package com.study.expandablerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.expandablerecyclerview.databinding.ListItemRecyclerviewBinding

class MainListAdapter(private var sampleDataList: ArrayList<SampleData>) :
    RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    inner class ViewHolder(private val mBinding: ListItemRecyclerviewBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(mSampleData: SampleData){
            mBinding.tvTitle.text = mSampleData.title
            mBinding.tvDate.text = mSampleData.date
            mBinding.tvContent.text = mSampleData.content

            mBinding.ctParent.setOnClickListener {
                if(mSampleData.expandable){
                    mBinding.ctChild.visibility = View.GONE
                    mBinding.ivDrop.setImageResource(R.drawable.drop_down_icon)
                    mSampleData.expandable = false
                }else{
                    mBinding.ctChild.visibility = View.VISIBLE
                    mBinding.ivDrop.setImageResource(R.drawable.drop_up_icon)
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