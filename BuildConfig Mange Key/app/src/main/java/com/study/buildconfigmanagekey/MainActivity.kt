package com.study.buildconfigmanagekey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(BuildConfig.API_KEY_1)
        println(BuildConfig.API_KEY_2)
    }
}