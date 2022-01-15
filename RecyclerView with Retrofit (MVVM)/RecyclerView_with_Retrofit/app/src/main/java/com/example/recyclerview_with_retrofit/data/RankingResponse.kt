package com.example.recyclerview_with_retrofit.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.json.JsonPrimitive
import org.json.JSONArray
import com.google.gson.JsonArray

data class RankingResponse (
    @SerializedName("status")
    val status: Int,
    @SerializedName("payload")
    val payloaod: JsonArray,
    @SerializedName("data")
    val data: JsonObject
)