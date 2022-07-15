package com.lge.cameraxwithpreviewview

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lge.cameraxwithpreviewview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private const val REQUIRED_PERMISSIONS = Manifest.permission.CAMERA
    }

    private lateinit var mBinding: ActivityMainBinding
    private var camera: Camera? = null
    private var cameraController: CameraControl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // 카메라 권한 확인
        if (allPermissionsGranted()) {
            startCamera() // 카메라 실행
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(REQUIRED_PERMISSIONS), REQUEST_CODE_PERMISSIONS)
        }
    }

    // 카메라 실행
    private fun startCamera() {

        // 1. CameraProvider 요청
        // ProcessCameraProvider는 Camera의 생명주기를 LifeCycleOwner의 생명주기에 Binding 함
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        // 2. CameraProvider를 요청한 후, 뷰를 만들 때 초기화에 성공했는지 확인
        cameraProviderFuture.addListener({
            // 3. 카메라를 선택하고 생명주기에 binding
            // 3-1. Preview를 만든다.
            // 3-2. 카메라 세팅을 한다. (+ useCases는 선택)
            // 3-3. 선택한 카메라를 생명주기에 binding 한다.
            // 3-4. Preview를 PreviewView에 연결한다.

            // Preview를 통해서 카메라 미리보기 화면을 구현.
            // surfaceProvider는 데이터를 받을 준비가 되었다는 신호를 카메라에게 보내준다.
            // setSurfaceProvider는 PreviewView에 SurfaceProvider를 제공해준다.
            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(mBinding.viewFinder.surfaceProvider)
            // preview.setSurfaceProvider 안쓰고 아래처럼 써도 됨
//               .also {
//               it.setSurfaceProvider(mBinding.viewFinder.surfaceProvider)
//           }

            // CameraSelector는 카메라 세팅을 맡는다.(전면, 후면 카메라)
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // 생명주기에 binding할 수 있는 ProcessCameraProvider 객체 가져옴
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            try {
                // binding 전에 binding 초기화
                cameraProvider.unbindAll()

                // 카메라를 생명주기에 binding 시키기 (bindToLifeCycle 통해서)
                camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview)

                cameraController = camera!!.cameraControl
                cameraController!!.setZoomRatio(1F) // 1x Zoom
            } catch (exc: Exception) {
                println("에러 $exc")
            }

            // 4. Preview를 PreviewView에 연결한다.
            // surfaceProvider는 데이터를 받을 준비가 되었다는 신호를 카메라에게 보내준다.
            // setSurfaceProvider는 PreviewView에 SurfaceProvider를 제공해준다.
            preview.setSurfaceProvider(mBinding.viewFinder.surfaceProvider)
        }, ContextCompat.getMainExecutor(this))
    }

    // 카메라 권한
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults:
        IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this, "접근 권한이 허용되지 않아 카메라를 실행할 수 없습니다. 설정에서 접근 권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 카메라 권한
    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        baseContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
}