package com.example.recyclerview_with_retrofit.api

import com.example.recyclerview_with_retrofit.data.RankingResponse
import retrofit2.Call
import retrofit2.http.GET

interface RankingService {
    @GET("/home/best-camper")
    fun rankingDisplay(): Call<RankingResponse>
}