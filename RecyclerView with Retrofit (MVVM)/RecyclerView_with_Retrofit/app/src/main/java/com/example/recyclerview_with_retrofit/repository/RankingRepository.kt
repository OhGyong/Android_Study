package com.example.recyclerview_with_retrofit.repository

import com.example.recyclerview_with_retrofit.api.CommonService.retrofit
import com.example.recyclerview_with_retrofit.api.RankingService
import com.example.recyclerview_with_retrofit.data.RankingResponse
import kotlinx.coroutines.runBlocking

class RankingRepository {

    /**
     * 2022.01.17 기존에 GlobalScope.launch 적용전에 되던 것
     */
//    fun getRankingData(): RankingResponse{
//
//        // create() 메서드를 사용하여 retrofit과 인터페이스를 연결
//        val response = retrofit.create(RankingService::class.java)
//
//        // 데이터 가져오기
//        val data = response.rankingDisplay().execute().body()
//        println("data 확인 $data")
//
//        // 데이터에서 payload만 꺼내기
//        val payload = data!!.payloaod
//        println("payload 확인 $payload")
//
//        // JsonArray에서 JsonObject로 바꿔서 값 하나씩 꺼내기
//        for(i in 0..payload.size()){
//            val payloadIndex = payload.get(i)
//            println("payload 인덱스 확인 $payloadIndex")
//            payloadIndex.asJsonObject.get("nickName")
//            println("payload 닉네임 확인 ${payloadIndex.asJsonObject.get("nickName")}")
//        }
//        return data
//    }


    /**
     * ViewModel, LiveData 사용
     */
    fun getRankingData(): RankingResponse {

        var data: RankingResponse? = null

        runBlocking {
            println("Repository")

            // 데이터 가져오기
            data = RankingApi.rankingResponse.rankingDisplay().execute().body()
        }
        return data!!
    }

    /**
     * create 함수 호출 시 리소스가 많이 들기 때문에
     * 싱글톤 객체 선언을 통해 한번만 생성되도록 설정
     */
    object RankingApi {
        val rankingResponse: RankingService by lazy{
            retrofit.create(RankingService::class.java)
        }
    }
}