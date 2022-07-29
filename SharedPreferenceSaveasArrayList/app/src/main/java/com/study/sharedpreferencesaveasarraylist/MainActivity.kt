package com.study.sharedpreferencesaveasarraylist

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.study.sharedpreferencesaveasarraylist.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mPrefs : SharedPreferences
    private lateinit var editPrefs: SharedPreferences.Editor
    private var stringPrefs : String? = null
    private var arrayListPrefs = ArrayList<searchData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        settingPrefs()

        // EditText 키보드 이벤트
        mBinding.etTextField.setOnEditorActionListener { textView, actionId, _ ->
            // 키보드에서 완료 버튼이 눌렸을 때 처리
            if(actionId == EditorInfo.IME_ACTION_DONE){
                arrayListPrefs.add(
                    0,
                    searchData(
                        textView.text.toString(),
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(System.currentTimeMillis()) // 현재 날짜
                    )
                )
                savePrefs()
            }
            false // false로 해야 키패드가 닫힘
        }

    }

    /**
     * SharedPreference 설정
     */
    private fun settingPrefs() {
        mPrefs = this.getSharedPreferences("station_search_list", MODE_PRIVATE) // prefs 불러오기
        editPrefs = mPrefs.edit() // prefs Edit 선언
        stringPrefs = mPrefs.getString("searchList", null)

        // prefs에 데이터가 있으면 String을 ArrayList로 변환
        if(stringPrefs != null && stringPrefs != "[]"){
            arrayListPrefs = GsonBuilder().create().fromJson(stringPrefs, object: TypeToken<ArrayList<searchData>>(){}.type)
        }
    }

    /**
     * SharedPreference 저장
     */
    private fun savePrefs() {
        // arrayList 타입을 json 형태의 String 타입으로 변환
        val toGson = GsonBuilder().create().toJson(
            arrayListPrefs,
            object : TypeToken<ArrayList<searchData>>() {}.type
        )
        editPrefs.putString("searchList", toGson) // prefs에 push
        editPrefs.apply() // prefs 저장
    }
}