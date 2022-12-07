package com.study.mqttwithssl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.study.mqttwithssl.databinding.ActivityMainBinding
import info.mqtt.android.service.MqttAndroidClient
import org.bouncycastle.util.io.pem.PemReader
import org.eclipse.paho.client.mqttv3.*
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.security.KeyFactory
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.spec.PKCS8EncodedKeySpec
import javax.net.SocketFactory
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

class MainActivity : AppCompatActivity() {
    // ssl 인증을 해야할 때 URI는 tcp가 아닌 ssl 이어야 함.
    private val serverUri: String = "ssl://ip 입력:포트 입력"  //서버 IP
    private val topic:String = "o_gyong test"	// 토픽

    private var sendText = ""
    private var receiveText = ""

    private lateinit var mBinding: ActivityMainBinding

    // Mqtt 방식의 통신을 지원하는 클래스, MqttAndroidClient 객체 생성
    private lateinit var mqttClient: MqttAndroidClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // MqttAndroidClient 초기화
        // 마지막 인자 값으로 Act의 AUTO_ACT와 MANUAL_ACK이 있는데 정확한 사용법을 모르겠음..
        // 기본적으로 AUTO_ACT로 동작함 → 메시지가 반환되면 프로세스에 메시지가 도착했다고 즉시 알림
        mqttClient = MqttAndroidClient(this, serverUri, MqttClient.generateClientId())

        // Mqtt의 Client가 서버에 연결하는 방법을 제어하는 클래스, MqttConnectOptions 객체 생성
        val mqttConnectOptions = MqttConnectOptions()

        // MqttConnectOptions의 소켓 팩토리 초기화
        mqttConnectOptions.socketFactory = mqttSSLAuth()

        // No subjectAltNames on the certificate match 에러 무시
        // TODO : 브로커의 IP와 호스트의 IP가 일치하는지를(인증) 무시하는 것 같음 → 다른 해결법 필요?
        mqttConnectOptions.isHttpsHostnameVerificationEnabled = false

        // Mqtt 서버와 연결
        // 연결 결과 콜백 → callbackConnectResult
        mqttClient.connect(mqttConnectOptions, null, callbackConnectResult)
    }


    /**
     * SSL 인증 처리
     */
    private fun mqttSSLAuth(password: String = ""): SocketFactory {
        // raw에 등록한 인증 파일들을 InputStream 변수로 만듬(파일을 읽기 위한 처리)
        val caInputStream: InputStream = BufferedInputStream(resources.openRawResource(R.raw.ca))
        val clientCrtInputStream: InputStream = BufferedInputStream(resources.openRawResource(R.raw.client_crt))
        val clientKeyInputStream: InputStream = BufferedInputStream(resources.openRawResource(R.raw.client_key))

        // X.509 인증서 타입의 CertificateFactory 객체 생성
        // → 각 파일들을 CertificateFactory 객체로 초기화 시키는데 사용.
        val certificateFactory = CertificateFactory.getInstance("X.509")

        // 파일 데이터로 Certificate 객체 생성 및 초기화
        val caCertificate  = certificateFactory.generateCertificate(caInputStream)
        val clientCertificate = certificateFactory.generateCertificate(clientCrtInputStream)

        // client key를 전달하여 PemReader 객체 생성
        // → Private Key로 된 '.key' 파일을 PemReader로 판독
        val keyPemReader = PemReader(InputStreamReader(clientKeyInputStream))

        // key 파일 내용을 ByteArray로 가져옴
        val pemContent = keyPemReader.readPemObject().content
        keyPemReader.close() // PemReader 종료(초기화)

        // PemReader로 구한 ByteArray를 PKCS #8 표준에 따라 인코딩
        // → PKCS #8는 일반적으로 PEM base64 인코딩 형식으로 변환된다고 함
        val keySpecPKCS8 = PKCS8EncodedKeySpec(pemContent)

        // 암호화 된 키를 기본 키로 변환하기 위해 KeyFactory 사용
        // → RSA로 암호화 되어있기 때문에 RSA 알고리즘을 전달하여 KeyFactory 객체 반환
        val keyFactory = KeyFactory.getInstance("RSA")

        // 인코딩 된 keySpec을 받아서 개인 키를 생성
        val privateKey = keyFactory.generatePrivate(keySpecPKCS8)

        // KeyStore를 이용하여 ca 인증서가 신뢰할 수 있는 인증서라고 정의
        // → 초기화와 신뢰할 수 있는 인증서로 정의를 안하면 ssl 인증에 실패
        val caKeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        caKeyStore.load(null, null) // 초기화
        caKeyStore.setCertificateEntry("ca-certificate", caCertificate) // 신뢰할 수 있는 인증서로 정의

        // 기본 TrustManagerFactory 알고리즘을 전달하여 TrustManagerFactory 객체 생성
        // KeyStore를 전달 받아 TrustManagerFactory를 초기화
        // → SSLContext를 초기화하는데 사용 예정
        val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        tmf.init(caKeyStore)

        // KeyStore를 이용하여 client_crt 인증서에 개인 키를 전달하고 신뢰할 수 있는 인증서라고 정의
        // → 초기화와 신뢰할 수 있는 인증서로 정의를 안하면 ssl 인증에 실패
        // → setKeyEntry를 이용해 개인 키로 client 인증서를 인증 (암호는 아무 값이나..?)
        val clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        clientKeyStore.load(null, null)
        clientKeyStore.setCertificateEntry("certificate", clientCertificate)
        clientKeyStore.setKeyEntry("private-key", privateKey, password.toCharArray(), arrayOf<Certificate>(clientCertificate))

        // 기본 KeyManagerFactory 알고리즘을 전달하여 KeyManagerFactory 객체 생성
        // KeyStore와 암호를 전달 받아 KeyManagerFactory를 초기화
        // → SSLContext를 초기화하는데 사용 예정
        // → 암호는 아무 값이나..?
        val kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        kmf.init(clientKeyStore, password.toCharArray())

        // MqttConnectOptions에 소켓팩토리를 정보를 주기 위해 SSLContext 객체 생성
        // KeyManagerFactory, TrustManagerFactory를 넘겨서 초기화
        val context = SSLContext.getInstance("TLSv1.3")
        context.init(kmf.keyManagers, tmf.trustManagers, null)

        return context.socketFactory
    }


    /**
     * connect 결과 처리
     */
    private var callbackConnectResult = object : IMqttActionListener {
        override fun onSuccess(asyncActionToken: IMqttToken?) {
            println("성공 $asyncActionToken")
            mqttClient.subscribe(topic, 1)

            mqttCallBack()
            sendMessageMqtt()
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
            println("실패 $exception")
        }
    }


    /**
     * 메시지 전송
     */
    private fun sendMessageMqtt() {
        mBinding.buttonSend.setOnClickListener {
            mqttClient.publish(topic, MqttMessage(mBinding.etSend.text.toString().toByteArray())) // 메세지 전송
            sendText = sendText + mBinding.etSend.text.toString() + "\n"
            mBinding.tvSend.text = sendText
        }
    }


    /**
     * 메시지 상태 콜백
     */
    private fun mqttCallBack() {
        // 콜백 설정
        mqttClient.setCallback(object : MqttCallback {
            // 연결이 끊겼을 경우
            override fun connectionLost(p0: Throwable?) {
                println("연결 끊어짐")
            }

            // 메세지가 도착했을 때
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                println("topic $topic")
                println("message $message")
                receiveText = receiveText + message.toString() + "\n\n"
                mBinding.tvReceive.text = receiveText
            }

            // 메시지 전송이 성공했을 때
            override fun deliveryComplete(p0: IMqttDeliveryToken?) {
                println("메세지 전송 성공")
            }
        })
    }
}