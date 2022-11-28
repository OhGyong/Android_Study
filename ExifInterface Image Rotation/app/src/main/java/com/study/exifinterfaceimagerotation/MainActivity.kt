package com.study.exifinterfaceimagerotation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.study.exifinterfaceimagerotation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private val galleryPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnMain.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission() {
        if(checkSelfPermission(galleryPermission) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(galleryPermission), 1)
        }else{
            intentGallery()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 1) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                intentGallery()
            }else{
                Toast.makeText(this, "권한 거절됨", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun intentGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        requestGalleryLauncher.launch(intent)
    }

    private val requestGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
//                mBinding.ivMain.setImageURI(it.data?.data)

                Glide.with(this)
                    .load(it.data?.data)
                    .into(mBinding.ivMain)

                mBinding.ivMain.visibility = View.VISIBLE
            }
        }
}