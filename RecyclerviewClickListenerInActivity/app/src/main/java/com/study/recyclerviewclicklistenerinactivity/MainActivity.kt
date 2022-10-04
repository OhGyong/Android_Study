package com.study.recyclerviewclicklistenerinactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.recyclerviewclicklistenerinactivity.databinding.ActivityMainBinding
import com.study.recyclerviewclicklistenerinactivity.databinding.ItemListMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: MainAdapter

    private val list = arrayListOf(
        "짱구", "철수", "유리", "훈이", "맹구"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mAdapter = MainAdapter(list)
        mBinding.rvMain.adapter = mAdapter
        mBinding.rvMain.layoutManager = LinearLayoutManager(this)

        mAdapter.onClickListener(object: OnClickListener {
            override fun setOnClickListener(itemData: String, binding: ItemListMainBinding) {
                Toast.makeText(this@MainActivity, itemData, Toast.LENGTH_SHORT).show()
            }
        })
    }
}