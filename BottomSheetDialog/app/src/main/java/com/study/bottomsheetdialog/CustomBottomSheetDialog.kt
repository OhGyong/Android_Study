package com.study.bottomsheetdialog

import android.content.Context
import android.os.Bundle
import android.view.Window
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.study.bottomsheetdialog.databinding.DialogBottomSheetBinding

class CustomBottomSheetDialog(context: Context) :
    BottomSheetDialog(context) {

    interface BtnClickListener {
        fun onBtnClick(dlg: CustomBottomSheetDialog)
    }

    private var mBinding = DialogBottomSheetBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(mBinding.root)
    }

    class Builder(context: Context) {
        private val mDialog = CustomBottomSheetDialog(context)

        fun setTitle(text: String): Builder {
            mDialog.mBinding.tvTitle.text = text
            return this
        }

        fun setContent(text: String): Builder {
            mDialog.mBinding.tvContent.text = text
            return this
        }

        fun setCancelBtn(): Builder {
            mDialog.mBinding.btnCancel.setOnClickListener {
                mDialog.dismiss()
            }
            return this
        }

        fun setCheckBtn(rightBtnClickListener: BtnClickListener? = null): Builder {
            mDialog.mBinding.btnCheck.setOnClickListener {
                rightBtnClickListener?.onBtnClick(mDialog)
            }
            return this
        }

        fun show(): CustomBottomSheetDialog {
            mDialog.show()
            return mDialog
        }
    }
}