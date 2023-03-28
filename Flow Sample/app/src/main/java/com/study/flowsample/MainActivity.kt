package com.study.flowsample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.study.flowsample.databinding.ActivityMainBinding
import com.study.flowsample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel.callSelect()

        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.sampleData.collect {
                println("MainActivity1 $it")
                mBinding.tvName.text = it.toString()

            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.returnCallSelect().collect {
                println("MainActivity2 $it")
            }
        }
    }
}