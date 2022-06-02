package com.example.tablayoutwithviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tablayoutwithviewpager.databinding.FragmentTwoBinding

class TwoFragment: Fragment() {
    private lateinit var mBinding: FragmentTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTwoBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}