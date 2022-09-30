package com.study.recyclerviewdraganddrop

import android.annotation.SuppressLint
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
        SampleData("샘플 제목6"), SampleData("샘플 제목5"), SampleData("샘플 제목4"),
        SampleData("샘플 제목3"), SampleData("샘플 제목2"), SampleData("샘플 제목1")
    )

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: MainListAdapter
    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mAdapter = MainListAdapter(object: MainListAdapter.ItemStartDragListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onEndDrag(initList: ArrayList<SampleData>) {
                println(initList)
                println("11")
            }
        })
//        mAdapter = MainListAdapter()

        val mCallback = RecyclerViewItemTouchHelperCallback(mAdapter)
        mItemTouchHelper = ItemTouchHelper(mCallback)
        mItemTouchHelper!!.attachToRecyclerView(mBinding.rvMain)

        mAdapter.setData(sampleList)
        mBinding.rvMain.adapter = mAdapter
        mBinding.rvMain.layoutManager = LinearLayoutManager(this)
    }
}