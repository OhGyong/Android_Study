# BottomNaviagtion 사용하기
BottomNavigationBar로 화면 이동하는 것 이외에 좌우 스크롤로 화면 이동을 하려면 ViewPager를 사용하면 된다.</br>
BottomNavigationBar의 컨텐츠는 5개까지 가능하다.


- https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView

1. build.gradle에 dependencies 추가
2. res-menu에 xml 파일 생성
3. activity_main.xml 파일 수정
4. 화면 전환을 위한 Fragment 생성
5. MainActivity.java 파일 수정

---


## 1. dependencies 추가
BottomNavigationView를 사용하기 위해서 Material Design을 dependencies에 추가해줘야 한다.</br>

build.gradle(Module) 파일에 `implementation 'com.google.android.material:material:1.0.0'`를 추가한다. <br>
![image](https://user-images.githubusercontent.com/52282493/121799290-40279b00-cc66-11eb-818d-46b981b67abb.png)

## 2. menu xml 추가
BottomNavigationView에 사용할 목록 등록 (이미지, 텍스트 순서) </br>
![image](https://user-images.githubusercontent.com/52282493/121799995-59324b00-cc6a-11eb-811d-4b1b5b6fa0e8.png)
![image](https://user-images.githubusercontent.com/52282493/121800019-7535ec80-cc6a-11eb-8cd5-e25d625e4371.png)

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/first"
        android:enabled="true"
        android:icon="@drawable/ic_menu"
        android:title="첫번째"
        app:showAsAction="ifRoom" />
    <item
        android:id="@+id/second"
        android:icon="@drawable/ic_location"
        android:title="두번째"
        app:showAsAction="ifRoom" />
    <item
        android:id="@+id/third"
        android:enabled="true"
        android:icon="@drawable/ic_gallery"
        android:title="세번째"
        app:showAsAction="ifRoom" />
</menu>
```

이미지는 Vector Asset으로 생성하여 drawable 폴더에 추가

## 3. activity_main.xml 수정
dependencies에 추가했던 material의 BottomNavigationView 추가</br>
![image](https://user-images.githubusercontent.com/52282493/121800151-2177d300-cc6b-11eb-9d01-d77dac347e3d.png)

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFFFFF">

    <FrameLayout
        android:id="@+id/fl_container"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/bnv_main"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bnv_main"
        android:layout_width="0dp"
        android:layout_height="?attr/actionBarSize"
        android:background="#FFFFFF"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/navi_menu" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

## 4. 이동할 Fragment 추가

![image](https://user-images.githubusercontent.com/52282493/121800200-60a62400-cc6b-11eb-8561-562a6c9101ae.png)

```kotlin
class FragmentFirst : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_first, container, false)
    }
}
```

## 5. MainActivity 수정

![image](https://user-images.githubusercontent.com/52282493/121802385-001ce400-cc77-11eb-82dc-5dc4be68e85e.png)

```kotlin
class MainActivity : AppCompatActivity() {
    private val fragmentOne by lazy { FragmentFirst() }
    private val fragmentTwo by lazy { FragmentSecond() }
    private val fragmentThree by lazy { FragmentThird() }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_main)
        initNavigationBar ()
    }

    private fun initNavigationBar() {
        val bnvMain : BottomNavigationView = findViewById(R.id.bnv_main)
        bnvMain.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.first -> {
                        changeFragment(fragmentOne)
                    }
                    R.id.second -> {
                        changeFragment(fragmentTwo)
                    }
                    R.id.third -> {
                        changeFragment(fragmentThree)
                    }
                }
                true
            }
            selectedItemId = R.id.first
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment).commit()
    }
}
```

## 결과

https://user-images.githubusercontent.com/52282493/121802494-8a654800-cc77-11eb-91a0-95095159e8e2.mp4