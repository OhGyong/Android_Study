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

        mBinding.etTextField.setOnEditorActionListener { textView, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                arrayListPrefs.add(
                    0,
                    searchData(
                        textView.text.toString(),
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(System.currentTimeMillis())
                    )
                )
                savePrefs()
            }
            false // false로 해야 키패드가 닫힘
        }

    }

    private fun settingPrefs() {
        mPrefs = this.getSharedPreferences("station_search_list", MODE_PRIVATE) // prefs 불러오기
        editPrefs = mPrefs.edit()
        stringPrefs = mPrefs.getString("searchList", null)

        // prefs에 데이터가 있으면 String을 ArrayList로 변환
        if(stringPrefs != null && stringPrefs != "[]"){
            arrayListPrefs = GsonBuilder().create().fromJson(stringPrefs, object: TypeToken<ArrayList<searchData>>(){}.type)
        }
        else{
            // TODO : 입력한 데이터 없음 표시
        }
    }

    private fun savePrefs() {
        val toGson = GsonBuilder().create().toJson(
            arrayListPrefs,
            object : TypeToken<ArrayList<searchData>>() {}.type
        )
        editPrefs.putString("searchList", toGson)
        editPrefs.apply()
        stringPrefs = mPrefs.getString("searchList", null)
    }
}