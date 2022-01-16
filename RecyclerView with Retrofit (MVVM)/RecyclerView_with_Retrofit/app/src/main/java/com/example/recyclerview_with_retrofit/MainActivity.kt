package com.example.recyclerview_with_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.recyclerview_with_retrofit.repository.RankingRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread{
            val response = RankingRepository().getRankingData()
//            println("메인 $response")
        }.start()


    }
}