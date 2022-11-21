package com.study.searchlistwithsharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.study.searchlistwithsharedpreferences.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: SearchListAdapter

    private lateinit var mPrefs : SharedPreferences
    private lateinit var mEditPrefs: SharedPreferences.Editor

    private var arrayListPrefs = ArrayList<PrefData>() // 저장할 ArrayList
    private var stringPrefs : String? = null // 저장할 때 사용할 문자열 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setAdapter()
        settingPrefs()
        setOnClick()
    }

    /**
     * SharedPreferences 설정
     */
    private fun settingPrefs() {
        mPrefs = getSharedPreferences("pref_file", MODE_PRIVATE) // SharedPreferences 불러오기
        mEditPrefs = mPrefs.edit() // SharedPreferences Edit 선언
        stringPrefs = mPrefs.getString("pref_data", null)

        // SharedPreferences 데이터가 있으면 String을 ArrayList로 변환
        // fromJson → json 형태의 문자열을 명시한 객체로 변환(두번째 인자)
        if(stringPrefs != null && stringPrefs != "[]"){
            arrayListPrefs = GsonBuilder().create().fromJson(
                stringPrefs, object: TypeToken<ArrayList<PrefData>>(){}.type
            )
            mAdapter.setList(arrayListPrefs) // Adapter에 데이터 넘김
        }
    }

    /**
     * SharedPreferences 저장
     */
    private fun savePrefs(inputText: String) {
        // ArrayList에 추가
        arrayListPrefs.add(
            0,
            PrefData(
                inputText,
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis()) // 현재 날짜
            )
        )

        // ArrayList를 json 형태의 String으로 변환
        // toJson → json으로 변환된 문자열 리턴
        stringPrefs = GsonBuilder().create().toJson(
            arrayListPrefs,
            object : TypeToken<ArrayList<PrefData>>() {}.type
        )
        mEditPrefs.putString("pref_data", stringPrefs) // SharedPreferences에 push
        mEditPrefs.apply() // SharedPreferences 적용

        mAdapter.setList(arrayListPrefs) // Adapter에 데이터 넘김
    }

    /**
     * 클릭 리스너
     */
    private fun setOnClick() {
        // EditText 키보드 이벤트
        mBinding.etTextField.setOnEditorActionListener { textView, actionId, _ ->
            // 키보드에서 완료 버튼이 눌렸을 때 처리
            if(actionId == EditorInfo.IME_ACTION_DONE){
                savePrefs(textView.text.toString()) // SharedPreferences에 저장
                mBinding.etTextField.text.clear() // 텍스트 클리어
            }
            false // false로 해야 키패드가 닫힘
        }
    }

    /**
     * Adapter 세팅
     */
    private fun setAdapter() {
        mAdapter = SearchListAdapter()
        mBinding.rvSearchList.adapter = mAdapter
    }
}