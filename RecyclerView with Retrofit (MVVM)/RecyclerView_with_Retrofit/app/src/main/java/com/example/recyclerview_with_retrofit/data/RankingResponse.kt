package com.example.recyclerview_with_retrofit.data

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class RankingResponse (
    @SerializedName("status")
    val status: Int,
    @SerializedName("payload")
    val payload: JsonArray,
    @SerializedName("data")
    val data: JsonObject
)