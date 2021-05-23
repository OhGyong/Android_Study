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

![image](https://user-images.githubusercontent.com/52282493/119254490-8d799500-bbf1-11eb-9ec4-14722c6b7f32.png)


![image](https://user-images.githubusercontent.com/52282493/119253732-8c466900-bbed-11eb-965d-88191045ff2e.png)

```
        android:usesCleartextTraffic="true"
```

---

## java 폴더 구성

![image](https://user-images.githubusercontent.com/52282493/119254792-5a380580-bbf3-11eb-8f56-98acca838e78.png)

---

### Interface 생성

![image](https://user-images.githubusercontent.com/52282493/119254812-7d62b500-bbf3-11eb-9786-7f7cf7081ff2.png)

``` kotlin
package com.example.retrofittest

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("/members/idle/mypage/update")
    fun apiSurvice(): Call<DataClass>
}
```

interface에서 API를 요청하고, 반환 값을 Call<객체타입>으로 지정 </br>
객체타입은 DataClass가 된다.

---

###  DataClass 생성

![image](https://user-images.githubusercontent.com/52282493/119255539-5c03c800-bbf7-11eb-9a06-902cfe24931d.png)

``` kotlin
data class DataClass(
    val request : String,
    val message : String,
    val data : String
)
```

API로 받을 json 형태의 data를 선언해준다.

---

### MainActivity에서의 처리

![image](https://user-images.githubusercontent.com/52282493/119255892-08927980-bbf9-11eb-8085-dd9e6feabbe9.png)

``` kotlin
package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var data: DataClass? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
//            .baseUrl("http://152.67.194.92:3000")
            .baseUrl("http://10.0.2.2:3000") // 로컬 환경 테스트
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val dataService: RetrofitService = retrofit.create(RetrofitService::class.java)
        dataService.apiSurvice().enqueue(object: Callback<DataClass>{
            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                t.message?.let { Log.e("data", it) }
            }

            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                if (response == null){
                    println("빈값빈값")
                }
                data = response.body()
                Log.e("data", data.toString())
                println("확인"+response.body()!!.request)

            }
        })
    }
}
```



