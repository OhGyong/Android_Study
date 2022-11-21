package com.study.tablayoutwithviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.study.tablayoutwithviewpager.databinding.FragmentThirdBinding

class ThreeFragment: Fragment() {
    private lateinit var mBinding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentThirdBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}