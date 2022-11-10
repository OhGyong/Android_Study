package com.study.cameraxwithcapture

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.study.cameraxwithcapture.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private const val REQUIRED_PERMISSIONS = Manifest.permission.CAMERA
    }

    private lateinit var mBinding: ActivityMainBinding
    private var camera: Camera? = null
    private var cameraController: CameraControl? = null

    private var imageCapture: ImageCapture? = null // 이미지 캡처를 위한 변수
    private lateinit var photoFile: File

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

        // 이미지 임시 파일 생성 -> 캐시 생성(data/data/패키지/cache)
        photoFile = File(
            applicationContext.cacheDir,
            "newImage.jpg"
        )

        mBinding.btnCapture.setOnClickListener {
            if(mBinding.btnCapture.text == "캡처하기"){
                // 사진 캡처 하기
                takePhoto()
            }else {
                // 사진 캡처 세팅
                mBinding.ivCapture.visibility = View.INVISIBLE
                mBinding.viewFinder.visibility = View.VISIBLE
                mBinding.btnCapture.text = getString(R.string.capture)

                // 임시 파일 삭제
                photoFile.delete()
            }
        }
    }

    /**
     * 카메라 실행
     */
    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(mBinding.viewFinder.surfaceProvider)

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // 이미지캡처 Builder 객체 생성
            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()

                camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

                cameraController = camera!!.cameraControl
                cameraController!!.setZoomRatio(1F) // 1x Zoom
            } catch (exc: Exception) {
                println("에러 $exc")
            }
            preview.setSurfaceProvider(mBinding.viewFinder.surfaceProvider)
        }, ContextCompat.getMainExecutor(this))
    }

    /**
     * 사진 캡처(촬영)
     */
    private fun takePhoto() {
        val mImageCapture = imageCapture ?: return

        // 이미지 임시 파일 생성(이름, 확장자, 파일 경로) -> 캐시 영역에 생김(data/data/패키지/cache)
//        val photoFile = File.createTempFile("image", ".jpeg") // 파일 경로 생략

        // ImageCapture.OutputFileOptions는 새로 캡처한 이미지를 저장하기 위한 옵션
        // 저장 위치 및 메타데이터를 구성하는데 사용
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // takePicture를 통해 사진을 촬영.
        mImageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Glide를 통해서 ImageView에 이미지 캡처한 이미지 설정
                    Glide.with(mBinding.ivCapture.context)
                        .load(Uri.fromFile(photoFile))
                        .into(mBinding.ivCapture)
                    mBinding.ivCapture.visibility = View.VISIBLE
                    mBinding.viewFinder.visibility = View.INVISIBLE
                    mBinding.btnCapture.text = getString(R.string.re_capture)

                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(applicationContext, "사진 촬영에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    /**
     * 권한 요청 결과를 판단(requestPermissions에 의해 호출)
     */
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

    /**
     * 카메라 권한 체크
     */
    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        baseContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
}