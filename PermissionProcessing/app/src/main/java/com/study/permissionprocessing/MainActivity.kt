package com.study.permissionprocessing

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_PERMISSION_CODE_1 = 1001
        const val REQUEST_PERMISSION_CODE_2 = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 버전에 따라 체크할 권한 array 생성
        val permissionArray =
            if (Build.VERSION.SDK_INT >= 31) {
                 arrayOf(
                    Manifest.permission.BLUETOOTH_ADVERTISE, // 블루투스
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.CAMERA // 카메라
                )
            }else {
                arrayOf(
                    Manifest.permission.CAMERA // 카메라
                )
            }

        val btnCheckPermission = findViewById<Button>(R.id.btn_permission_request)
        btnCheckPermission.setOnClickListener {
            if(Build.VERSION.SDK_INT >= 31){
                // 블루투스와 카메라 권한이 허용되었는지 체크
                if(permissionArray.all{ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
                    Toast.makeText(this, "블루투스와 카메라 권한이 모두 허용되어 있습니다.", Toast.LENGTH_SHORT).show()
                }
                // 권한 요청
                else{
                    requestPermissions(permissionArray, REQUEST_PERMISSION_CODE_1)
                }
            }
            else if(Build.VERSION.SDK_INT < 31 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // 카메라 권한이 허용되었는지 체크
                if(permissionArray.all{ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
                    Toast.makeText(this, "카메라 권한이 허용되어 있습니다.", Toast.LENGTH_SHORT).show()
                }
                // 권한 요청
                else{
                    requestPermissions(permissionArray, REQUEST_PERMISSION_CODE_2)
                }
            }
        }

        val btnIntent = findViewById<Button>(R.id.btn_intent_activity)
        btnIntent.setOnClickListener {
            val intent = Intent(this, OneByOneProcessActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * requestPermissions()의 결과를 처리하는 함수.
     * - shouldShowRequestPermissionRationale()은 사용자가 권한 요청을 명시적으로 거부한 경우에 true를 반환한다.
     *      → 권한 허용했을 때 처리를 해준다면, 이것을 이용하여 다시 묻지 않음을 처리할 수 있다.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            // 다시 묻지 않음을 생각하고 처리하는 법.
            REQUEST_PERMISSION_CODE_1 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "모든 권한을 허용하였습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "블루투스 권한을 허용하였습니다.", Toast.LENGTH_SHORT).show()
                        }else if(shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_ADVERTISE)){
                            Toast.makeText(this, "블루투스 권한을 거절하였습니다.", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "블루투스 권한을 다시 묻지 않음을 하였습니다.", Toast.LENGTH_SHORT).show()
                        }

                        if(grantResults[3] == PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "카메라 권한을 허용하였습니다.", Toast.LENGTH_SHORT).show()
                        }else if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                            Toast.makeText(this, "카메라 권한을 거절하였습니다.", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "카메라 권한을 다시 묻지 않음을 하였습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
            // 다시 묻지 않음을 생각하지 않고 처리.
            REQUEST_PERMISSION_CODE_2 -> {
                // 권한 허용일 때
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "카메라 권한을 허용하였습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "카메라 권한을 거절하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}