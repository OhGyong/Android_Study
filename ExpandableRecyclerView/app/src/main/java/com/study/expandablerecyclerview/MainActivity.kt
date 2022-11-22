package com.study.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.expandablerecyclerview.databinding.ActivityMainBinding

data class SampleData(
    val title: String,
    val date: String,
    val content: String,
    var expandable: Boolean = false
)

class MainActivity : AppCompatActivity() {

    private var sampleList = arrayListOf(
        SampleData(
            "공지3",
            "2022.04.11",
            "공지3\n" +
                    "본문입니다.\n" +
                    "본문입니다.\n" +
                    "본문입니다."
        ),
        SampleData(
            "공지2",
            "2022.03.23",
            "공지2\n" +
                    "본문입니다.\n" +
                    "본문입니다."
        ),
        SampleData(
            "공지1",
            "2022.03.18",
            "공지1\n" +
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