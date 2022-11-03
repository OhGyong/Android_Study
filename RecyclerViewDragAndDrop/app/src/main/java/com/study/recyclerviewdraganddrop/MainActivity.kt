package com.study.recyclerviewdraganddrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.recyclerviewdraganddrop.databinding.ActivityMainBinding

data class SampleData(
    val title: String,
)

class MainActivity : AppCompatActivity() {

    private var sampleList = arrayListOf(
        SampleData("샘플 제목5"), SampleData("샘플 제목4"),
        SampleData("샘플 제목3"), SampleData("샘플 제목2"), SampleData("샘플 제목1")
    )

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: MainListAdapter
    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mAdapter = MainListAdapter()

        val mCallback = RecyclerViewItemTouchHelperCallback(mAdapter)
        mItemTouchHelper = ItemTouchHelper(mCallback)
        mItemTouchHelper?.attachToRecyclerView(mBinding.rvMain) // ItemTouchHelper를 RecyclerView에 연결

        mAdapter.setData(sampleList)
        mBinding.rvMain.adapter = mAdapter
        mBinding.rvMain.layoutManager = LinearLayoutManager(this)

        mAdapter.itemDragListener(object : ItemStartDragListener{
            override fun onDropActivity(
                initList: ArrayList<SampleData>,
                changeList: ArrayList<SampleData>
            ) {
                println(initList) // 최초 리스트
                println(changeList) // Drag and Drop 이후 리스트
            }
        })
    }


}