# 단일 Fragment로 TabLayout 구성하기

 서버에서 받아온 데이터로 Tab의 항목이 유동적으로 변하고 각 탭의 화면이 거의 유사하게 구성되어 있다면, TabLayout의 `addTab()` 함수와 `단일 Fragment`을 사용하여 효율적으로 개발할 수 있다.
 <br/>

activity_main에서 TabLayout을 등록하고 탭의 항목을 누르면 선택한 항목의 텍스트를 Fragment에서 보여주는 프로젝트를 예제로 들려고 한다.<br/>
TabLayout의 addTab()을 통해 Tab의 텍스트를 설정하고, `addOnTabSelectedListener()`를 통해서 탭의 item을 눌렀을 때의 이벤트 처리를 관리한다.<br/>
addOnTabSelectedListener()은 리스너이기 때문에 화면을 처음 실행하게 되면 FrameLayout에 아무것도 표시되지 않는다. 때문에 리스너 전에 Fragment에 초기값을 설정해줘야 한다. `Bundle()`과 `arguments`를 통해서 Fragment에 데이터를 보내주고, activity_main에서 Fragment를 담을 레이아웃으로 FrameLayout을 사용하였는데 `beginTransaction()`과 `commit()`을 통하여 FrameLayout에 Fragment를 연결시켜준다.<br/>
이후에 탭 리스너를 통해 화면을 갱신해주면 된다.


https://user-images.githubusercontent.com/105263450/171011202-505c277c-ca7f-409c-9cee-0aa59bc8d853.mp4

--- 

### activity_main
```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout 
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <View
            android:id="@+id/v_main1"
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="@color/white"
            app:layout_constraintTop_toTopOf="parent"/>

        <com.google.android.material.tabs.TabLayout
            android:id="@+id/tab_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabGravity="center"
            app:tabIndicatorColor="#333333"
            app:tabIndicatorHeight="2dp"
            app:tabRippleColor="@android:color/transparent"
            app:tabSelectedTextColor="#333333"
            app:tabTextAppearance="@style/tab_text_Style"
            app:tabTextColor="#868C96"
            app:layout_constraintTop_toBottomOf="@+id/v_main1"/>

        <View
            android:id="@+id/v_main2"
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="@color/white"
            app:layout_constraintTop_toBottomOf="@id/tab_main"/>
        
        <FrameLayout
            android:id="@+id/fl_main"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/v_main2" />

    </androidx.constraintlayout.widget.ConstraintLayout>
```

### fragment_main
```kotlin
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/tv_fragment"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="30sp"
            android:textColor="@color/black"
            android:textStyle="bold"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
        
    </androidx.constraintlayout.widget.ConstraintLayout>
```

### MainActivity
```kotlin
    class MainActivity : AppCompatActivity() {

        private lateinit var mBinding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mBinding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)

            initView()
        }

        private fun initView() {
            setTabLayout()
        }

        private fun setTabLayout() {
            mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("첫번째"))
            mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("두번째"))
            mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("세번째"))
            mBinding.tabMain.addTab(mBinding.tabMain.newTab().setText("네번째"))

            val bundle = Bundle()
            bundle.putString("data", "첫번째")

            val fragment = MainFragment()
            fragment.arguments = bundle

            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fl_main, fragment)
            transaction.commit()

            mBinding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    fragment.changeTextView(tab!!.text.toString())
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }
```


### MainFragment
```kotlin
    class MainFragment : Fragment() {
        private lateinit var mBinding: FragmentMainBinding

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            mBinding = FragmentMainBinding.inflate(inflater, container, false)

            mBinding.tvFragment.text = requireArguments().getString("data")

            return mBinding.root
        }

        fun changeTextView(string: String) {
            mBinding.tvFragment.text = string
        }
    }
```