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
        "가", "나", "다", "라", "마"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mAdapter = MainAdapter(list)
        mBinding.rvMain.adapter = mAdapter
        mBinding.rvMain.layoutManager = LinearLayoutManager(this)

        mAdapter.setOnClickListenerFunc(object: SetOnClickListenerInterface {
            override fun listItemClickListener(itemData: String, binding: ItemListMainBinding) {
                Toast.makeText(this@MainActivity, itemData, Toast.LENGTH_SHORT).show()
            }
        })
    }
}