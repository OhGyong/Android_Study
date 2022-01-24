package com.example.recyclerview_with_retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerview_with_retrofit.data.RankingResponse
import com.example.recyclerview_with_retrofit.repository.RankingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RankingViewModel: ViewModel() {

    val rankingData: MutableLiveData<RankingResponse> = MutableLiveData()

    fun getRanking(){
        GlobalScope.launch {
            try{
                println("ViewModel 연결")
                val data = RankingRepository().getRankingData()
                rankingData.postValue(data)
            }catch (e: Error){
                println("Ranking data get fail")
            }
        }
    }
}