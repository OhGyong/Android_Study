package com.study.bottomsheetdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.study.bottomsheetdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnOpen.setOnClickListener {
            CustomBottomSheetDialog.Builder(this)
                .setTitle("다이얼로그")
                .setContent("다이얼로그 생성")
                .setCheckBtn(object: CustomBottomSheetDialog.BtnClickListener{
                    override fun onBtnClick(dlg: CustomBottomSheetDialog) {
                        Toast.makeText(this@MainActivity, "완료", Toast.LENGTH_LONG).show()
                    }
                })
                .setCancelBtn()
                .show()
        }
    }
}