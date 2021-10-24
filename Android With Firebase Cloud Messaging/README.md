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

        /**
        * 메세지를 수신할 때 호출된다.
        * remoteMessage는 수신한 메시지.
        * 메시지의 유형은 notification과 data가 있는데 notification은 foreground일 때만 푸시 알림이 오고,
        * data는 foreground와 background일 때 알림이 온다.
        */
        override fun onMessageReceived(remoteMessage: RemoteMessage) {
            super.onMessageReceived(remoteMessage)
            Log.d("onMessageReceived 호출", "From: ${remoteMessage.from}")

            if(remoteMessage.data.isNotEmpty()){
                Log.d("알림 data 확인", "Message data payload: ${remoteMessage.data}")
                sendNotification(remoteMessage.data["title"].toString(),remoteMessage.data["body"].toString())
            }


        }

        /**
        * FCM 등록 토큰이 업데이트되면 호출된다.(앱 삭제 및 재설치 등의 이유로 토큰이 변경될 수 있음)
        */
        override fun onNewToken(token: String) {
            Log.d("토큰 확인", "Refreshed token: $token")
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

## Postman에서 테스트
![image](https://user-images.githubusercontent.com/52282493/138599492-c14341ef-1663-4c36-8a6e-eab17566f858.png)</br>

Header의 KEY 값 중 Authorization은 Firebase 설정에서 클라우드 메시징의 서버 키를 넣어준다.</br>
![image](https://user-images.githubusercontent.com/52282493/138599602-13c92818-8552-4684-9173-e9897c485e81.png)
