package com.study.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.study.paging3.data.SampleData
import com.study.paging3.data.SampleDatabase
import com.study.paging3.databinding.ActivityMainBinding
import com.study.paging3.repository.SampleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
//
//        mViewModel.getList(1)
//        mViewModel.sampleListObserve.observe(this) { result ->
//            println("리스트 호출 결과 : $result")
//
//            if(result.isNullOrEmpty()) {
//                return@observe
//            }
//        }
        lifecycleScope.launch {
            mViewModel.getContent().collectLatest {
                println("home $it")
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
