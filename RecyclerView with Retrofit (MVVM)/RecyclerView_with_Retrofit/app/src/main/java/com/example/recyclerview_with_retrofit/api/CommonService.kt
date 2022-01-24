package com.example.recyclerview_with_retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommonService {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}