# ViewPager로 TabLayout 구성하기

탭의 항목을 누르거나 스와이프 하여 선택한 항목의 텍스트를 보여주는 프로젝트를 만들어 보려고 한다.<br/>
우선 activity_main에서 TabLayout과 ViewPager2를 등록하고, 내가 보여줄 화면의 Fragment를 정의해준다.<br/>
ViewPager를 적용하기 총 page를 계산하고 그려야 하기 때문에 Adapter가 필요하다. Adapter를 생성하고 Activity에서 Adapter에 내가 사용할 Fragment 정보를 넘겨주면 ViewPager를 사용할 준비는 끝난다.<br/>
마지막으로 Activity에서 `TabLayoutMediator`를 사용하여 Tab의 설정을 해주면 마무리 된다.

![TabLayoutWithViewPager2Gif](https://user-images.githubusercontent.com/105263450/172046410-71e8a745-af63-4eba-a32f-e08f5f9a1d8a.gif)

--- 

### activity_main
```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemㄹas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:background="@color/white"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.google.android.material.tabs.TabLayout
            android:id="@+id/tab_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabGravity="center"
            app:tabIndicatorColor="#333333"
            app:tabIndicatorHeight="2dp"
            app:tabRippleColor="@android:color/transparent"
            app:tabSelectedTextColor="#333333"
            app:tabTextColor="#868C96"
            app:layout_constraintTop_toTopOf="parent"/>

        <View
            android:id="@+id/v_main"
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="#B6B3B3"
            app:layout_constraintTop_toBottomOf="@id/tab_main"/>

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/vp_main"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/v_main" />

    </androidx.constraintlayout.widget.ConstraintLayout>
```

### fragment_first, second, third, fourth
```kotlin
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/first"
            android:textSize="30sp"
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
        private lateinit var viewPagerAdapter: ViewPagerAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mBinding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)

            faqViewPagerInit()
            faqTabInit()
        }

        // ViewPager 설정
        private fun faqViewPagerInit() {
            val fragmentList = listOf(
                OneFragment(),
                TwoFragment(),
                ThreeFragment(),
                FourFragment()
            )
            viewPagerAdapter = ViewPagerAdapter(this)
            viewPagerAdapter.fragments.addAll(fragmentList)
            mBinding.vpMain.adapter = viewPagerAdapter
        }

        // TabLayout 설정
        private fun faqTabInit() {
            TabLayoutMediator(mBinding.tabMain, mBinding.vpMain) { tab, pos ->
                tab.text = pos.toString()
                when (pos) {
                    0 -> tab.text = getString(R.string.first)
                    1 -> tab.text = getString(R.string.second)
                    2 -> tab.text = getString(R.string.third)
                    3 -> tab.text = getString(R.string.fourth)
                }
            }.attach()
        }
    }
```


### MainFragment
```kotlin
    class OneFragment: Fragment() {
        private lateinit var mBinding: FragmentFirstBinding

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            mBinding = FragmentFirstBinding.inflate(inflater, container, false)
            return mBinding.root
        }
    }
```