package com.example.recyclerview_with_retrofit.repository

import com.example.recyclerview_with_retrofit.api.CommonService.retrofit
import com.example.recyclerview_with_retrofit.api.RankingService
import com.example.recyclerview_with_retrofit.data.RankingResponse

class RankingRepository {

    fun getRankingData(): RankingResponse{

        val response = retrofit.create(RankingService::class.java)
        val data = response.rankingDisplay().execute()
        println("확인 ${data.body()}")
        return data.body()!!
    }
}