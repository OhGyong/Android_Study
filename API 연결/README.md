# API 연결

**안드로이드 연결은 retrofit 라이브러리를 사용한다.**

---

### build.gragdle(:app)에 dependecies 추가

![image](https://user-images.githubusercontent.com/52282493/119253449-4046f480-bbec-11eb-882f-b4eb0a9501ee.png)

```
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.0'
```

---

### AndroidManifest.xml에 cleartext 트래픽 설정 부여

![image](https://user-images.githubusercontent.com/52282493/119253732-8c466900-bbed-11eb-965d-88191045ff2e.png)

![image](https://user-images.githubusercontent.com/52282493/119254490-8d799500-bbf1-11eb-9ec4-14722c6b7f32.png)

```
        android:usesCleartextTraffic="true"
```



