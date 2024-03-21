package com.study.flowsample.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.study.flowsample.data.ColdEntity
import com.study.flowsample.data.HotEntity
import com.study.flowsample.databinding.ActivityMainBinding
import com.study.flowsample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

        // Cold Flow
        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.selectCold().collectLatest {
                println("ColdFlow $it")
                mBinding.tvCold.text = it.toString()
            }
        }

        // Hot Flow
//        mViewModel.selectHot()
//        CoroutineScope(Dispatchers.Main).launch {
//            mViewModel.hotData.collectLatest {
//                println("HotFlow $it")
//                mBinding.tvHot.text = it.toString()
//            }
//        }

        // Hot Flow
        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.selectHot2().collectLatest {
                println("HotFlow $it")
                mBinding.tvHot.text = it.toString()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // insert 버튼
        mBinding.inInsert.btnInsert.setOnClickListener {
            mViewModel.insertCold(ColdEntity(mBinding.inInsert.etInsert.text.toString()))
            mViewModel.insertHot(HotEntity(mBinding.inInsert.etInsert.text.toString()))
            btnSuccess(mBinding.inInsert.etInsert)
        }

        // delete 버튼
        mBinding.inDelete.btnDelete.setOnClickListener {
            mViewModel.deleteCold(mBinding.inDelete.etDelete.text.toString())
            mViewModel.deleteHot(mBinding.inDelete.etDelete.text.toString())
            btnSuccess(mBinding.inDelete.etDelete)
        }

        // update 버튼
        mBinding.inUpdate.btnUpdate.setOnClickListener {
            mViewModel.updateCold(
                mBinding.inUpdate.etOrigin.text.toString(), mBinding.inUpdate.etChange.text.toString()
            )
            mViewModel.updateHot(
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