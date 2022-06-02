package com.example.tablayoutwithviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tablayoutwithviewpager.databinding.FragmentThreeBinding

class ThreeFragment: Fragment() {
    private lateinit var mBinding: FragmentThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentThreeBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}