package com.study.drawerlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.study.drawerlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // drawer 잠금
        mBinding.dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        // 열기 버튼
        mBinding.btnMain.setOnClickListener {
            mBinding.dlMain.open()
//            mBinding.dlMain.openDrawer(GravityCompat.START)
//            mBinding.dlMain.openDrawer(Gravity.LEFT)
        }

        // 닫기 버튼
        mBinding.inViewDrawer.ivDrawerClose.setOnClickListener {
            mBinding.dlMain.close()
//            mBinding.dlMain.closeDrawer(GravityCompat.START)
//            mBinding.dlMain.closeDrawer(Gravity.LEFT)
        }
    }
}