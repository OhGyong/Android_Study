package com.study.textwatcher

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.study.textwatcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val passwordNum = "487259"

        mBinding.etMain.addTextChangedListener {
            if(mBinding.etMain.length() == 6) {
                mBinding.tvMain.visibility = View.VISIBLE
                if(mBinding.etMain.text.toString() == passwordNum){
                    mBinding.tvMain.text = getString(R.string.auth_num_success)
                    mBinding.tvMain.setTextColor(Color.GREEN)
                    mBinding.vMain.setBackgroundColor(Color.BLACK)
                }else {
                    mBinding.tvMain.text = getString(R.string.auth_num_fail)
                    mBinding.tvMain.setTextColor(Color.RED)
                    mBinding.vMain.setBackgroundColor(Color.RED)
                }
            }else {
                mBinding.tvMain.visibility = View.GONE
                mBinding.vMain.setBackgroundColor(Color.BLACK)
            }
        }

        mBinding.etMain.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })
    }
}