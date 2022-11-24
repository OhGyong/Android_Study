package com.study.encryptedsharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.study.encryptedsharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val masterKeyAlias = MasterKey
            .Builder(applicationContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        /**
         * 암호화된 SharedPreferences 객체 생성
         */
        val pref = EncryptedSharedPreferences.create(
            this, // Context
            "encrypted_pref_file", // 파일 명
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // AES256_SIV으로 key를 암호화
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // AES256_GCM으로 value를 암호화
        )

        /**
         * 암호화 하여 저장
         */
        mBinding.etMain.setOnEditorActionListener { textView, actionId, _ ->
            // 키보드에서 완료 버튼이 눌렸을 때 처리
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                pref.edit().putString("myKey", textView.text.toString()).apply()
                mBinding.etMain.clearFocus()
            }
            false
        }

        /**
         * 암호화 한 데이터 불러오기
         */
        mBinding.btnMain.setOnClickListener {
            mBinding.tvMain.text = pref.getString("myKey", "")
        }
    }
}