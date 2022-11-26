package com.study.recyclerviewclicklistenerinactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.recyclerviewclicklistenerinactivity.databinding.ItemListMainBinding

interface SetOnClickListenerInterface {
    fun listItemClickListener(itemData: String, binding: ItemListMainBinding)
}

class MainAdapter(private val mList: ArrayList<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var onClickListener: SetOnClickListenerInterface? = null

    fun listItemClickFunc(pOnClick: SetOnClickListenerInterface) {
        this.onClickListener = pOnClick
    }

    inner class ViewHolder(private val itemViewBinding: ItemListMainBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(mItemData: String){
            itemViewBinding.tvName.text = mItemData

            if(adapterPosition != RecyclerView.NO_POSITION){
                itemViewBinding.ctMain.setOnClickListener {
                    onClickListener?.listItemClickListener(mItemData, itemViewBinding)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListMainBinding = ItemListMainBinding.inflate(
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
}