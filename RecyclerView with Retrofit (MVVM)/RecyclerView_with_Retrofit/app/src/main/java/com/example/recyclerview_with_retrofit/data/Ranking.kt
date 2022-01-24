package com.example.recyclerview_with_retrofit.data

import com.google.gson.JsonElement

data class Ranking(
    val id: Int,
    val nickName: String,
    val rank: Int,
    val totalFire: Int
)