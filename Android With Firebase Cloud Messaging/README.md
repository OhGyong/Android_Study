# 안드로이드에서 Firebase Cloud Messaging 사용하기

안드로이드 스튜디오 상단 탭에서 Tools의 Firebase를 클릭한다.</br>
![image](https://user-images.githubusercontent.com/52282493/138554990-f0cc57fa-74bf-46f8-8e8f-f3d3783850ee.png)

Assistant 속성으로 Firebase 화면이 생성된다.</br>
![image](https://user-images.githubusercontent.com/52282493/138555817-0f7900d9-5597-4def-b6c6-57a4cdf856fc.png)

Cloud Messaging을 클릭하면 Set up Firebase Cloud Messaging으로 넘어가지는데 해당 순서에 맞게 진행하면 된다.</br>
![image](https://user-images.githubusercontent.com/52282493/138556044-6713cf61-92f6-4dca-95b9-60e7ae6dc82d.png)

1. Connet your app to Firebase

2. Add FCM to  your app

3. Access the device registration token</br>
Firebase에서 사용하는 기기 토큰을 접속하는 방법이 설명.</br>
하지만 이 방법은 더 이상 사용되지 않는다.

4. Handle messages
FCM 메시지를 받았을 때 처리하는 방법 설명.

```kotlin
    class MyFirebaseMessagingService : FirebaseMessagingService() {
        private val TAG: String = this.javaClass.simpleName

        override fun onMessageReceived(remoteMessage: RemoteMessage) {
            super.onMessageReceived(remoteMessage)
            if (remoteMessage.notification != null) {
                sendNotification(remoteMessage.notification?.title, remoteMessage.notification!!.body!!)
            }
        }

        override fun onNewToken(token: String) {
            Log.d(TAG, "new Token: $token")

            // 토큰 값을 따로 저장해둔다.
            val pref = this.getSharedPreferences("token", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("token", token).apply()
            editor.commit()

            Log.i("로그: ", "성공적으로 토큰을 저장함")
        }

        // 받은 알림을 기기에 표시하는 메서드
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

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
        }
    }
```

![image](https://user-images.githubusercontent.com/52282493/138563243-a5d5d09d-2416-4462-8594-434718110770.png)</br>
![image](https://user-images.githubusercontent.com/52282493/138563232-7dd3421e-bc45-412c-9e02-226e7e18b812.png)