package com.study.recyclerviewpagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.recyclerviewpagination.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mAdapter : ActivityAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setAdapter()

        val sampleList = resources.getStringArray(R.array.first_page).toList() as ArrayList<String>
        mAdapter.setList(sampleList)

        mBinding.rvMain.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 리사이클러뷰 아이템 위치 찾기, 아이템 위치가 완전히 보일때 호출됨
                val rvPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()

                // 리사이클러뷰 아이템 총개수 (index 접근 이기 때문에 -1)
                val totalCount =
                    recyclerView.adapter?.itemCount?.minus(1)

                if(rvPosition == totalCount) {
                    page++
                    if(page == 2) {
                        sampleList.addAll(resources.getStringArray(R.array.second_page).toList() as ArrayList<String>)
                        mAdapter.setList(resources.getStringArray(R.array.second_page).toList() as ArrayList<String>)
                    }else if (page ==3) {
                        sampleList.addAll(resources.getStringArray(R.array.third_page).toList() as ArrayList<String>)
                        mAdapter.setList(resources.getStringArray(R.array.third_page).toList() as ArrayList<String>)
                    }
                    println(sampleList)
                }
            }
        })
    }

    private fun setAdapter() {
        mAdapter = ActivityAdapter()
        mBinding.rvMain.adapter = mAdapter
    }
}
