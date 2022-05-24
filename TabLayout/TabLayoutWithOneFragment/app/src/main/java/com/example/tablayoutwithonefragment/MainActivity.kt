package com.example.tablayoutwithonefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablayoutwithonefragment.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
    }

    private fun initView() {
        setTabLayout()
    }

    private fun setTabLayout() {
        mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("첫번째"))
        mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("두번째"))
        mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("세번째"))
        mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("네번째"))

        val bundle = Bundle()
        val fragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        bundle.putString("data", "첫번째")
        fragment.arguments = bundle
        transaction.add(mBinding.flMain.id, fragment)
        transaction.commit()


        mBinding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                bundle.putString("data", tab!!.text.toString())
                val fragment2 = MainFragment()
                fragment2.arguments = bundle

                val transaction2 = supportFragmentManager.beginTransaction()
                transaction2.add(mBinding.flMain.id, fragment2)
                transaction2.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }
}