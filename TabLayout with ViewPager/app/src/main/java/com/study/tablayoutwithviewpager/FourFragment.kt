package com.study.tablayoutwithviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.study.tablayoutwithviewpager.databinding.FragmentFourthBinding

class FourFragment: Fragment() {
    private lateinit var mBinding: FragmentFourthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFourthBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}