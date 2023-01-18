package com.study.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.paging3.data.SampleData
import com.study.paging3.data.SampleDatabase
import com.study.paging3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mViewModel : MainViewModel
    private lateinit var mAdapter : SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        SampleDatabase.getInstance(this)

        setAdapter()

        /**
         * collectLatest로 수집한 데이터를 Adapter에 전달 (collect를 써도 정상동작 함)
         */
        lifecycleScope.launch {
            mViewModel.getContent().collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
        }

        /**
         * PagingDataAdapter의 로드 상태를 전달받아 refresh가 로딩 상태이면 로딩 화면 띄우도록 처리
         */
        lifecycleScope.launch {
            mAdapter.loadStateFlow.collect { loadState ->
                mBinding.pbMainLoading.isVisible = loadState.refresh is LoadState.Loading
            }
        }

        /**
         * 아이템 삭제 이후 PagingDataAdapter의 refresh를 호출하여 UI 갱신
         */
        mViewModel.itemDeleteObserve.observe(this) {
            mAdapter.refresh()
        }

    }

    private fun setAdapter() {
        mAdapter = SampleAdapter()

        // PagingDataAdapter에 LoadStateAdapter를 연결하여 로딩 view 추가
        mBinding.rvMain.adapter = mAdapter.withLoadStateFooter(
            footer = SampleLoadStateAdapter()
        )

        mBinding.rvMain.layoutManager = LinearLayoutManager(mBinding.rvMain.context)

        mAdapter.removeListener(object : SampleAdapter.CustomListenerInterface {
            override fun removeListener(position: Int, sampleData: SampleData) {
                mViewModel.setItemDelete(sampleData.id)
            }
        })
    }
}
