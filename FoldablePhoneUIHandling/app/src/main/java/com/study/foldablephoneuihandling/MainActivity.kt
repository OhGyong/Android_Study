package com.study.foldablephoneuihandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.foldablephoneuihandling.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private var deviceType = "NORMAL" // 기기 타입 저장 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        getDeviceType()
    }

    /**
     * 기기 타입 찾기
     */
    private fun getDeviceType() {
        val display = applicationContext?.resources?.displayMetrics

        deviceType =
            if(display == null) {
                "NORMAL"
            }else {
                // 태블릿, 폴드 펼침
                if(display.widthPixels > 1600) {
                    "TABLET"
                }
                // 미니, 폴드 닫힘
                else if(display.widthPixels < 980) {
                    "MINI"
                }
                // 일반
                else{
                    "NORMAL"
                }
            }
    }
}