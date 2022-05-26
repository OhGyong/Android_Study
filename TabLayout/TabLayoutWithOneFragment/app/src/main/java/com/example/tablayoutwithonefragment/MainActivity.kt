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

        val initBundle = Bundle()
        initBundle.putString("data", "첫번째")

        val initFragment = MainFragment()
        initFragment.arguments = initBundle

        val initTransaction = supportFragmentManager.beginTransaction()
        initTransaction.add(R.id.fl_main, initFragment)
        initTransaction.commit()


        mBinding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val bundle = Bundle()
                val fragment = MainFragment()
                val transaction = supportFragmentManager.beginTransaction()
                bundle.putString("data", tab!!.text.toString())
                fragment.arguments = bundle
                transaction.add(R.id.fl_main, fragment)
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }
}