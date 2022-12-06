package com.study.mqttwithlocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.study.mqttwithlocal.databinding.ActivityMainBinding
import info.mqtt.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MainActivity : AppCompatActivity() {
    private val serverUri:String = "tcp://ip 입력:1883"  //서버 IP
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
        // → 인자 값으로 Act의 AUTO_ACT와 MANUAL_ACK이 있는데 정확한 사용법을 모르겠음..
        // →→ 기본적으로 AUTO_ACT로 동작함 : 메시지가 반환되면 프로세스에 메시지가 도착했다고 즉시 알림
        mqttClient = MqttAndroidClient(this, serverUri, MqttClient.generateClientId())

        // Mqtt 서버와 연결
        // MqttConnectOptions는 Mqtt의 Client가 서버에 연결하는 방법을 제어하는 클래스
        // 연결 결과 콜백 → callbackConnectResult
        mqttClient.connect(MqttConnectOptions(), null, callbackConnectResult)
    }


    /**
     * connect 결과 처리
     */
    private var callbackConnectResult = object : IMqttActionListener {
        override fun onSuccess(asyncActionToken: IMqttToken?) {
            println("성공 $asyncActionToken")
            // 연결에 성공하면 해당 토픽 구독
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