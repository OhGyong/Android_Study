package com.study.permissionprocessing

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.study.permissionprocessing.MainActivity.Companion.REQUEST_PERMISSION_CODE_1
import com.study.permissionprocessing.MainActivity.Companion.REQUEST_PERMISSION_CODE_2

class OneByOneProcessActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_by_one_process)

        val btnCheckPermission = findViewById<Button>(R.id.btn_permission_request)
        btnCheckPermission.setOnClickListener {
            if(Build.VERSION.SDK_INT >= 31) {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADVERTISE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "블루투스 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
                }else {
                    requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_ADVERTISE), REQUEST_PERMISSION_CODE_1)
                }

                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "카메라 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
                }else {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION_CODE_2)
                }
            }else {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "카메라 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_PERMISSION_CODE_1 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "블루투스 권한을 허용하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_PERMISSION_CODE_2 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "카메라 권한을 허용하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}