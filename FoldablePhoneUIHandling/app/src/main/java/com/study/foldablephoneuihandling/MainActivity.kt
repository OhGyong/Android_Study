package com.study.foldablephoneuihandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import com.study.foldablephoneuihandling.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private var deviceType = "NORMAL" // 기기 타입 저장 변수

    private lateinit var llLayoutParams1: ViewGroup.LayoutParams
    private lateinit var llLayoutParams2: ViewGroup.LayoutParams
    private lateinit var llLayoutParams3: ViewGroup.LayoutParams
    private lateinit var llLayoutParams4: ViewGroup.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        getDeviceType()
        setBtnSize()
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

    /**
     * 버튼 크기 정하기
     */
    private fun setBtnSize() {

        llLayoutParams1 = mBinding.ll1.layoutParams
        llLayoutParams2 = mBinding.ll2.layoutParams
        llLayoutParams3 = mBinding.ll3.layoutParams
        llLayoutParams4 = mBinding.ll4.layoutParams

        when(deviceType) {
            "MINI" -> {
                setLayoutParams(llLayoutParams1, 60F)
                setLayoutParams(llLayoutParams2, 60F)
                setLayoutParams(llLayoutParams3, 60F)
                setLayoutParams(llLayoutParams4, 60F)
            }
            "NORMAL" -> {
                setLayoutParams(llLayoutParams1, 75F)
                setLayoutParams(llLayoutParams2, 75F)
                setLayoutParams(llLayoutParams3, 75F)
                setLayoutParams(llLayoutParams4, 75F)
            }
            "TABLET" -> {
                setLayoutParams(llLayoutParams1, 90F)
                setLayoutParams(llLayoutParams2, 90F)
                setLayoutParams(llLayoutParams3, 90F)
                setLayoutParams(llLayoutParams4, 90F)
            }
            else -> {
                setLayoutParams(llLayoutParams1, 75F)
                setLayoutParams(llLayoutParams2, 75F)
                setLayoutParams(llLayoutParams3, 75F)
                setLayoutParams(llLayoutParams4, 75F)
            }
        }

        mBinding.ll1.layoutParams = llLayoutParams1
        mBinding.ll2.layoutParams = llLayoutParams2
        mBinding.ll3.layoutParams = llLayoutParams3
        mBinding.ll4.layoutParams = llLayoutParams4
    }


    /**
     * LinearLayout의 크기 변환
     */
    private fun setLayoutParams(llLayoutParams: ViewGroup.LayoutParams, value: Float) {
        llLayoutParams.width = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            resources?.displayMetrics
        ).toInt()

        llLayoutParams.height = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            resources?.displayMetrics
        ).toInt()
    }
}