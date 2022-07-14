package com.lge.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lge.expandablerecyclerview.databinding.ActivityMainBinding

data class SampleData(
    val title: String,
    val date: String,
    val content: String,
    var expandable: Boolean = false
)

class MainActivity : AppCompatActivity() {

    private var sampleList = arrayListOf(
        SampleData(
            "샘플 제목3",
            "2022.04.11",
            "본문입니다." +
                    "본문입니다." +
                    "본문입니다." +
                    "본문입니다."
        ),
        SampleData(
            "샘플 제목2",
            "2022.03.23",
            "본문입니다." +
                    "본문입니다." +
                    "본문입니다." +
                    "본문입니다."
        ),
        SampleData(
            "샘플 제목1",
            "2022.03.18",
            "본문입니다." +
                    "본문입니다." +
                    "본문입니다." +
                    "본문입니다."
        )
    )

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.rvMain.adapter = MainListAdapter(sampleList)
        mBinding.rvMain.layoutManager = LinearLayoutManager(this)
    }
}