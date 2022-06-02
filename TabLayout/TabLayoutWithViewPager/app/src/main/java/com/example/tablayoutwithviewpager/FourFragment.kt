package com.example.tablayoutwithviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tablayoutwithviewpager.databinding.FragmentFourBinding

class FourFragment: Fragment() {
    private lateinit var mBinding: FragmentFourBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFourBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}