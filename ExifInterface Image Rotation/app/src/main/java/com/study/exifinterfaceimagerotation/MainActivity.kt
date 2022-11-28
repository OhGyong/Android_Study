package com.study.exifinterfaceimagerotation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.exifinterface.media.ExifInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.study.exifinterfaceimagerotation.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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

    /**
     * 파일 접근 권한 체크(갤러리 진입)
     */
    private fun checkPermission() {
        if(checkSelfPermission(galleryPermission) == PackageManager.PERMISSION_DENIED) {
            // 권한 거절 시 권한 요청
            requestPermissions(arrayOf(galleryPermission), 1)
        }else{
            // 권한 허용 시 갤러리로 이동
            intentGallery()
        }
    }

    /**
     * 권한 요청 결과 처리
     */
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

    /**
     * 갤러리 화면으로 이동
     */
    private fun intentGallery() {
        // Intent의 Action 값으로 ACTION_GET_CONTENT, ACTION_PICK 둘 중 하나 사용
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        requestGalleryLauncher.launch(intent)
    }

    /**
     * 갤러리 화면에서 이미지 선택 이후 처리
     */
    private val requestGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {

                // 선택한 이미지의 uri가 null 이면 종료
                if(it.data?.data == null ){
                    return@registerForActivityResult
                }

                var uri = it.data?.data!!

                // 원본 이미지 등록
                Glide.with(this)
                    .load(uri)
                    .apply(
                        // 이전 이미지를 재활용하지 않도록 처리
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                    )
                    .into(mBinding.ivMain1)


                try {
                    val inputStream = contentResolver.openInputStream(uri)
                    val exif = ExifInterface(inputStream!!)

                    inputStream.close()

                    // 회전 정보 알아내기
                    var orientation =
                        exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
                    when (orientation) {
                        ExifInterface.ORIENTATION_ROTATE_90 -> orientation = 90
                        ExifInterface.ORIENTATION_ROTATE_180 -> orientation = 180
                        ExifInterface.ORIENTATION_ROTATE_270 -> orientation = 270
                    }

                    // 화면이 회전되었을 때 matrix로 회전한 각도만큼 회전.
                    // 회전한 새 이미지 생성 후 캐시에 저장
                    if(orientation >= 90){
                        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))

                        val matrix = Matrix()
                        matrix.setRotate(90f, bitmap.width.toFloat(), bitmap.height.toFloat())
                        val newImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)

                        // 캐시에 임시 파일 생성
                        val cacheFile = File(
                            applicationContext.cacheDir,
                            "newImage.jpg"
                        )

                        // 파일 데이터 입력
                        val outputStream = FileOutputStream(cacheFile)
                        newImg.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream.close()

                        uri = cacheFile.toUri()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }


                Glide.with(this)
                    .load(uri)
                    .apply(
                        // 이전 이미지를 재활용하지 않도록 처리
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                    )
                    .into(mBinding.ivMain2)

                mBinding.ivMain1.visibility = View.VISIBLE
                mBinding.ivMain2.visibility = View.VISIBLE
                mBinding.tvMain1.visibility = View.VISIBLE
                mBinding.tvMain2.visibility = View.VISIBLE
            }
        }
}