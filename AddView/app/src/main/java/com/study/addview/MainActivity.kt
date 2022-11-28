package com.study.addview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.study.addview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btn1.setOnClickListener {
            setAddView(mBinding.tv1)
        }

        mBinding.btn2.setOnClickListener {
            setAddView(mBinding.tv2)
        }

        mBinding.btn3.setOnClickListener {
            setAddView(mBinding.tv3)
        }
    }

    private fun setAddView(tv: TextView) {
        (tv.parent as ViewGroup).removeView(tv) // textView의 부모 뷰 그룹에서 textView 제거
        mBinding.llMain.addView(tv) // LinearLayout에 textView 추가
        tv.visibility = View.VISIBLE
    }
}