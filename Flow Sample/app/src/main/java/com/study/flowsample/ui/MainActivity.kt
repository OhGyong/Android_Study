package com.study.flowsample.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.study.flowsample.data.FlowEntity
import com.study.flowsample.data.StateFlowEntity
import com.study.flowsample.databinding.ActivityMainBinding
import com.study.flowsample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()
    private lateinit var  inputMethodManager : InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        mViewModel.selectStateFlow()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Flow
                launch {
                    mViewModel.selectFlow().collectLatest {
                        println("     Flow: $it")
                        mBinding.tvFlow.text = it.toString()
                    }
                }

                // StateFlow
                launch {
                    mViewModel.stateFlowData.collectLatest {
                        println("StateFlow: $it")
                        mBinding.tvStateFlow.text = it.toString()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // insert 버튼
        mBinding.inInsert.btnInsert.setOnClickListener {
            mViewModel.insertFlow(FlowEntity(mBinding.inInsert.etInsert.text.toString()))
            mViewModel.insertStateFlow(StateFlowEntity(mBinding.inInsert.etInsert.text.toString()))
            btnSuccess(mBinding.inInsert.etInsert)
            println("-------")
        }

        // delete 버튼
        mBinding.inDelete.btnDelete.setOnClickListener {
            mViewModel.deleteFlow(mBinding.inDelete.etDelete.text.toString())
            mViewModel.deleteStateFlow(mBinding.inDelete.etDelete.text.toString())
            btnSuccess(mBinding.inDelete.etDelete)
        }

        // update 버튼
        mBinding.inUpdate.btnUpdate.setOnClickListener {
            mViewModel.updateFlow(
                mBinding.inUpdate.etOrigin.text.toString(), mBinding.inUpdate.etChange.text.toString()
            )
            mViewModel.updateStateFlow(
                mBinding.inUpdate.etOrigin.text.toString(), mBinding.inUpdate.etChange.text.toString()
            )
            btnSuccess(mBinding.inUpdate.etOrigin)
            btnSuccess(mBinding.inUpdate.etChange)
        }
    }

    /**
     * 버튼 클릭 후 키보드 내리고 텍스트 클리어
     */
    private fun btnSuccess(edt: EditText) {
        inputMethodManager.hideSoftInputFromWindow(edt.windowToken, 0)
        edt.text.clear()
    }
}