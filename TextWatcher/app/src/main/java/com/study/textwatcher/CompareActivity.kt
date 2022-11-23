package com.study.textwatcher

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.study.textwatcher.databinding.ActivityCompareBinding

class CompareActivity: Activity() {

    private lateinit var mBinding: ActivityCompareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityCompareBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.etCompare.addTextChangedListener(object: TextWatcher {
            /**
             * 텍스트 변경 전
             * s:기존 문자열, start:커서 시작 위치, count:변경 전 단어 수, after:변경 후 문자 수
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, after: Int) {
//                println("기존 문자열: $s")
//                println("커서 시작 위치: $start")
//                println("변경 전 문자 수: before")
//                println("변경 후 문자 수: $after")
            }

            /**
             * 텍스트 변경 시
             * s:변경된 문자열, start:커서 시작 위치, before:변경 대상 문자 수, count:변경 후 문자 수
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, after: Int) {
                println("-")
//                println("기존 문자열: $s")
//                println("커서 시작 위치: $start")
//                println("변경 전 문자 수: before")
//                println("변경 후 문자 수: $after")
            }

            /**
             * 텍스트 변경 후
             * s:변경된 문자열
             */
            override fun afterTextChanged(p0: Editable?) {
                println("-")
            }

        })
    }
}