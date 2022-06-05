package com.example.tablayoutwithviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablayoutwithviewpager.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        faqViewPagerInit()
        faqTabInit()
    }

    // ViewPager 설정
    private fun faqViewPagerInit() {
        val fragmentList = listOf(
            OneFragment(),
            TwoFragment(),
            ThreeFragment(),
            FourFragment()
        )
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)
        mBinding.vpMain.adapter = viewPagerAdapter
    }

    // TabLayout 설정
    private fun faqTabInit() {
        TabLayoutMediator(mBinding.tabMain, mBinding.vpMain) { tab, pos ->
            tab.text = pos.toString()
            when (pos) {
                0 -> tab.text = getString(R.string.first)
                1 -> tab.text = getString(R.string.second)
                2 -> tab.text = getString(R.string.third)
                3 -> tab.text = getString(R.string.fourth)
            }
        }.attach()
    }
}