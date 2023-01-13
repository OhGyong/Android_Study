package com.study.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.paging3.data.SampleDatabase
import com.study.paging3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mViewModel : MainViewModel
    private lateinit var mAdapter : ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        SampleDatabase.getInstance(this)

        setAdapter()

        /**
         * collectLatest로 수집한 데이터를 어댑터에 전달 (collect를 써도 정상동작 함)
         * submitData가 suspend 함수이기 때문에 코루틴을 사용함.
         */
        lifecycleScope.launch {
            mViewModel.getContent().collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun setAdapter() {
        mAdapter = ActivityAdapter()
        mBinding.rvMain.adapter = mAdapter
        mBinding.rvMain.layoutManager =LinearLayoutManager(mBinding.rvMain.context)
    }
}
