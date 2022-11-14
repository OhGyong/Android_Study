package com.study.viewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerstudy.R

class ViewPagerAdapter(imageList: ArrayList<Int>): RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    var item = imageList

    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.image.setImageResource(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }
}