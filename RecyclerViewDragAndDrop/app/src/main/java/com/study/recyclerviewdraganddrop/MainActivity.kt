package com.study.recyclerviewdraganddrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
                // TODO : 드랍됐을 때 처리
//               Toast.makeText(
//                   this@MainActivity,
//                   "initList: $initList \n changeList: $changeList ",
//                   Toast.LENGTH_SHORT
//               ).show()
            }
        })
    }
}