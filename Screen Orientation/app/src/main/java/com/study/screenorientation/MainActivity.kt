package com.study.screenorientation

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(requestedOrientation)
        println("---")
        println(resources.configuration.orientation)
        println("---")
        println(Configuration.ORIENTATION_PORTRAIT)
        println(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        println("---")
        println(Configuration.ORIENTATION_LANDSCAPE)
        println(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        val btn = findViewById<Button>(R.id.btn_main)
        btn.setOnClickListener {
            requestedOrientation = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }
}