package com.study.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.paging3.data.SampleData
import com.study.paging3.databinding.ListItemMainBinding

interface CustomListenerInterface {
    fun removeListener(position: Int, sampleData: SampleData)
}

class ActivityAdapter: RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    private val mList = ArrayList<SampleData>()
    private var onRemoveListener: CustomListenerInterface? = null

    fun removeListener(pOnClick: CustomListenerInterface) {
        this.onRemoveListener = pOnClick
    }


    inner class ViewHolder(private val mBinding : ListItemMainBinding): RecyclerView.ViewHolder(mBinding.root) {
        fun bind(listData: SampleData) {
            mBinding.tvItem.text = listData.title

            // 클릭하고자 하는 view의 리스너에 데이터 전달
            if(bindingAdapterPosition != RecyclerView.NO_POSITION){
                mBinding.ivItemRemove.setOnClickListener {
                    onRemoveListener?.removeListener(bindingAdapterPosition, listData)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(notifyList: ArrayList<SampleData>) {
        mList.addAll(notifyList)
        notifyItemRangeChanged(0, mList.size)
    }

    fun addLastData(sampleData: SampleData) {
        mList.add(sampleData)
        notifyItemInserted(mList.size-1)
    }

    fun removeItem(sampleData: SampleData) {
        val deleteIndex = mList.indexOf(sampleData)
        mList.remove(sampleData)

        notifyItemRemoved(deleteIndex)
    }
}