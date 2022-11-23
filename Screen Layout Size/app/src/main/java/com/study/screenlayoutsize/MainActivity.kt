package com.study.screenlayoutsize

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvType = findViewById<TextView>(R.id.tv_type)

        val screenSizeType = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK

        when (screenSizeType) {
            Configuration.SCREENLAYOUT_SIZE_SMALL -> {
                tvType.text = getString(R.string.small)
            }
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> {
                tvType.text = getString(R.string.normal)
            }
            Configuration.SCREENLAYOUT_SIZE_LARGE -> {
                tvType.text = getString(R.string.large)
            }
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> {
                tvType.text = getString(R.string.x_large)
            }
            else -> {
                tvType.text = getString(R.string.unknown)
            }
        }
    }
}