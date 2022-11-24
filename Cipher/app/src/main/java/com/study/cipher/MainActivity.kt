package com.study.cipher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.inputmethod.EditorInfo
import com.study.cipher.databinding.ActivityMainBinding
import java.security.DigestException
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    /**
     * 암호화, 복호화에 사용할 변수
     */
    private val iv = ByteArray(16) // 초기화 벡터에 사용할 값
    private val keySpec = SecretKeySpec(hashSHA256(), "AES") // 바이트 배열의 데이터 키를 비밀키로 변환
    private val cipherInstance = Cipher.getInstance("AES/CBC/PKCS5Padding") // Cipher 객체에 알고리즘 옵션 부여.
    private var encText = "" // 암호화 한 text를 담을 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.etMain.setOnEditorActionListener { _, actionId, _ ->
            // 키보드에서 완료 버튼이 눌렸을 때 처리
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mBinding.etMain.clearFocus()
            }
            false
        }

        /**
         * 암호화 시작
         */
        mBinding.btnEnc.setOnClickListener {
            // 암호화 작업
            cipherInstance.init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(iv))
            val byteArrayEncText = cipherInstance.doFinal(mBinding.etMain.text.toString().toByteArray())
            encText = String(Base64.encode(byteArrayEncText, Base64.DEFAULT))
            mBinding.tvEnc.text = encText
        }

        /**
         * 복호화 시작
         */
        mBinding.btnDnc.setOnClickListener {
            // 복호화 작업
            try {
                cipherInstance.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(iv))
                val byteArrayDecText = Base64.decode(encText, Base64.DEFAULT)
                mBinding.tvDec.text = String(cipherInstance.doFinal(byteArrayDecText))
            } catch (e: Exception) {
                // TODO : 복호화 실패 알림?
            }
        }
    }

    /**
     * 주어진 바이트 배열과 알고리즘을 사용하여 해시키를 반환.
     */
    private fun hashSHA256(): ByteArray {
        val hash: ByteArray
        try {
            val md = MessageDigest.getInstance("SHA-256") // 길이에 따라 16Byte → AES128, 24Byte → AES192, 32Byte → AES256 로 처리된다고 함.
            md.update(ByteArray(1)) // MessageDigest를 업데이트하여 초기화 시켜준다. 어떤 값을 넣어야 하는지는 잘 모르겠음.
            hash = md.digest() // Hash 계산을 완료하여 Hash 값의 바이트 배열을 생성
        } catch (e: CloneNotSupportedException) {
            throw DigestException("couldn't make digest of partial content")
        }
        return hash
    }
}