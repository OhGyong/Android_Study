package com.example.recyclerview_with_retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_with_retrofit.R
import com.example.recyclerview_with_retrofit.data.Ranking
import com.example.recyclerview_with_retrofit.databinding.RecyclerRankingBinding

class RankingAdapter(private val list: ArrayList<Ranking>) : RecyclerView.Adapter<RankingAdapter.RankingViewHolder>() {


    /**
     * 데이터 바인딩 쓰지않고 LiveData만 사용했을 때
     */
//    inner class RankingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private val userName: TextView = itemView.findViewById(R.id.user_name)
//        private val bornfireNum: TextView = itemView.findViewById(R.id.bonfire_num)
//
//        fun bind(data: Ranking){
//            userName.text = data.nickName
//            bornfireNum.text = data.totalFire.toString()
//        }
//    }

    /**
     * 데이터 바인딩 썼을 때
     */
    inner class RankingViewHolder(private val binding: RecyclerRankingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(ranking: Ranking){
            binding.rankingItem = ranking
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        // 데이터 바인딩 쓰지않고 LiveData만 사용했을 때
//        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_ranking, parent ,false)
//        return RankingViewHolder(adapterLayout)

        // 데이터 바인딩 썼을 때
        val binding = RecyclerRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}