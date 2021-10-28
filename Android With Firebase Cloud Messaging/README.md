# Node 서버에서 Firebase 서버를 거쳐 안드로이드에서 알람 띄우기

## Firebase Cloud Message(FCM) 소개
[Firebase 클라우드 메시징 소개 사이트](https://firebase.google.com/docs/cloud-messaging/?authuser=1#implementation_paths)

FCM은 무료로 메시지를 안정적으로 전송할 수 있는 교차 플랫폼 메시징 솔루션이다. 즉 FCM은 앱에 알람 기능을 사용할 수 있게하는 서비스이다.

FCM은 2가지 유형의 메시지를 클라이언트(안드로이드)에 보낼 수 있는데, 하나는 `notification`이고 다른 하나는 `data` 형식이며 두 가지 형식을 함께 보내도 된다.</br>
- `notification(알림 메시지)`</br>
포그라운드에서 onMessageReceived() 메서드에 구현한 대로 작동을 하는 반면, 백그라운드에서는 자동으로 title과 body를 뽑아와 push를 하게 되어 커스텀을 할 수 없다.
- `data(데이터 메시지)`</br>
포그라운드와 백그라운드에서 onMessageReceived() 메서드에 구현한 대로 작동한다.

보통 data형식을 사용하여 구현하는 경우가 많다.

![image](https://user-images.githubusercontent.com/52282493/139264517-ced07d1c-b57f-42d2-b61c-5a5272e654f9.png)</br>
node 서버에서 Firebase 서버를 거쳐 안드로이드에 알람을 띄우려고 한다. 이것을 구현하는 것을 FCM Push 구현이라고 한다.

## 서버 설정
서버에서 사용하기 위해서 비공개 키를 다운받아야 한다.</br>
![서버스 계정 생성](https://user-images.githubusercontent.com/52282493/139266009-abbae32b-9e6c-412f-b862-06af33b5d1c0.PNG)</br>
Firebase에서 해당 앱의 프로젝트 설정 페이지로 이동하여 서비스 계정 탭에서 새 비공개 키 생성을 눌러 json 파일을 받아서 serviceAccount에 등록한다.

```kotlin
    const admin = require('firebase-admin')
    let serviceAccount = require('')
    router.post('/test', (req, res)=>{
        if(!admin.apps.length){
            admin.initializeApp({
                credential: admin.credential.cert(serviceAccount)
            })
        }

        let message = {
            token: '',
            data: {
                title: "node에서 title",
                body: "node에서의 data",
                clickAction: "SubActivity"
            }
        }

        try {
            admin
            .messaging()
            .send(message)
            .then(res.send("보내짐"))
            
        } catch (error) {
            res.send(error)
        }
    })
```


## 안드로이드 설정
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
