package com.study.tablayoutwithonefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.study.tablayoutwithonefragment.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var mBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(inflater, container, false)

        mBinding.tvFragment.text = requireArguments().getString("data")

        return mBinding.root
    }

    fun changeTextView(string: String) {
        mBinding.tvFragment.text = string
    }
}