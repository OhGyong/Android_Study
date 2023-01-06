package com.study.recyclerviewpaginationremoveitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.study.recyclerviewpaginationremoveitem.data.SampleData
import com.study.recyclerviewpaginationremoveitem.data.SampleDatabase
import com.study.recyclerviewpaginationremoveitem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mViewModel : MainViewModel
    private lateinit var mAdapter : ActivityAdapter
    private lateinit var mSampleDB : SampleDatabase

    private var page = 1
    private var listSize = 0
    private var deleteItem: SampleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mSampleDB = Room.databaseBuilder(
            applicationContext, SampleDatabase::class.java, "sample_database"
        ).build()

        observeLiveData()
        setAdapter()

        mViewModel.getSampleListSize(mSampleDB)
    }

    private fun observeLiveData() {
        mViewModel.sampleListSizeObserve.observe(this) {
            println("리스트 사이즈 호출 결과 : $it")

            if(it == 0 || it == null) {
                return@observe
            }

            listSize = it-1
            mViewModel.getSampleList(mSampleDB, page)
        }

        mViewModel.sampleListObserve.observe(this) {
            println("리스트 호출 결과 : $it")

            if(it.isNullOrEmpty()) {
                return@observe
            }

            mAdapter.setList(it)
        }

        mViewModel.itemDeleteObserve.observe(this) {
            println("아이템 삭제 호출")

            mAdapter.removeItem(deleteItem!!)
        }
    }

    private fun setAdapter() {
        mAdapter = ActivityAdapter()
        mBinding.rvMain.adapter = mAdapter

        mAdapter.removeListener(object: CustomListenerInterface{
            override fun removeListener(position: Int, sampleData: SampleData) {
                mViewModel.setItemDelete(mSampleDB, sampleData.id)
                deleteItem = sampleData
            }
        })

        mBinding.rvMain.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 리사이클러뷰 아이템 위치 찾기, 아이템 위치가 완전히 보일때 호출됨
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()

                // 리사이클러뷰 아이템 총 개수 (index 접근 이기 때문에 -1)
                val totalCount =  recyclerView.adapter?.itemCount?.minus(1)

                // 페이징 처리
                if(rvPosition == totalCount && listSize != totalCount && listSize!=0) {
                    mViewModel.getSampleList(mSampleDB, ++page)
                }
            }
        })
    }
}
