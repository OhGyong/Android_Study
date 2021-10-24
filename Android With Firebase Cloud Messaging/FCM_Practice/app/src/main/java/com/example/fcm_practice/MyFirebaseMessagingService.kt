package com.example.fcm_practice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    /**
     * 메세지를 수신할 때 호출된다.
     * remoteMessage는 수신한 메시지.
     * 메시지의 유형은 notification과 data가 있는데 notification은 foreground일 때만 푸시 알림이 오고,
     * data는 foreground와 background일 때 알림이 온다.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("알림 onMessageReceived 호출", "From: ${remoteMessage.from}")
        println("알림 From: ${remoteMessage.from}")
        println("알림 아이디 ${FirebaseInstanceId.getInstance().token}")

        if(remoteMessage.data.isNotEmpty()){
            Log.d("알림 data 확인", "Message data payload: ${remoteMessage.data}")
            sendNotification(remoteMessage.data["title"].toString(),remoteMessage.data["body"].toString())
        }

        remoteMessage.notification?.let {
            Log.d("알림 notification 확인", "Message notification payload: ${remoteMessage.notification}")
            sendNotification(remoteMessage.notification!!.title, remoteMessage.notification!!.body.toString())
        }



    }

    /**
     * FCM 등록 토큰이 업데이트되면 호출된다.(앱 삭제 및 재설치 등의 이유로 토큰이 변경될 수 있음)
     */
    override fun onNewToken(token: String) {
        Log.d("알림 토큰 확인", "Refreshed token: $token")
    }


    /**
     * 수신 된 FCM 메시지를 포함하여 간단한 알림을 만들고 표시한다.
     * onMessagedReceived() 메서드에서 FCM이 보낸 title과 body 등의 정보를 알아와서 세부 설정을 한다.
     */
    private fun sendNotification(title: String?, body: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = getString(R.string.firebase_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 예외처리
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 , notificationBuilder.build())
    }
}