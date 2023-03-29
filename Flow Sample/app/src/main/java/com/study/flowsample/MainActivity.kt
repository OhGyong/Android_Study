package com.study.flowsample

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.study.flowsample.data.Sample
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
    private lateinit var  inputMethodManager : InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // db에 저장된 내용 조회
        mViewModel.callSelect()

        /**
         * select 결과 받기
         */
        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.sampleData.collect {
                mBinding.tvName.text = it.toString()
            }
        }

        /**
         * return으로 Select 결과 전달 받기
         */
        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.returnCallSelect().collect {
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // insert 버튼
        mBinding.inInsert.btnInsert.setOnClickListener {
            mViewModel.callInsert(Sample(mBinding.inInsert.etInsert.text.toString()))
            btnSuccess(mBinding.inInsert.etInsert)
        }

        // delete 버튼
        mBinding.inDelete.btnDelete.setOnClickListener {
            mViewModel.callDelete(mBinding.inDelete.etDelete.text.toString())
            btnSuccess(mBinding.inDelete.etDelete)
        }

        // update 버튼
        mBinding.inUpdate.btnUpdate.setOnClickListener {
            mViewModel.callUpdate(
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