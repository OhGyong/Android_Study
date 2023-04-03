package com.study.flowsample.ui

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
         * 1-1.
         * - Dao에서 Flow로 반환
         * - ViewModel에서 StateFlow로 처리 (캡슐화)
         * 이전 데이터와 비교하여 같을 경우 collect 되지 않음. 새로운 데이터일 경우에만 collect
         */
        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.sampleData.collect {
                println("callSelect1 $it")
                mBinding.tvName.text = it.toString()
            }
        }

        /**
         * 1-2.
         * return으로 Select 결과 전달 받기
         */
        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.returnCallSelect().collect {
            }
        }

        /**
         * 2.
         * - Dao에서 List로 반환
         * - Flow로 받음
         * 첫 데이터에만 반응, 값의 변화에 대해 반응 없음
         */
        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.callSelect2().collect {
                println("callSelect2 $it")
            }
        }

        /**
         * 3.
         * - Dao에서 List로 반환
         * - StateFlow로 받음
         *  첫 데이터에만 반응, 값의 변화에 대해 반응 없음
         */
        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.callSelect3().collect {
                println("callSelect3 $it")
            }
        }

        /**
         * 4.
         * - Dao에서 Flow로 반환
         * - Flow로 받음
         * 값의 변화에 따라 호출됨
         * 이전 데이터에 상관없이 모든 값을 발행
         */
        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.callSelect4().collect {
                println("callSelect4 $it")
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