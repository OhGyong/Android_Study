# ViewPager를 RecyclerView로 만들기

ViewPager를 통해 간단하게 이미지만 표현해보려고 한다.<br/>
ViewPager를 구현하는 방법으로 RecyclerView를 사용해 볼 것이다.

1. build.gradle에 의존성 추가
2. ViewPager를 사용할 액티비티의 xml에 ViewPager2 속성 추가
3. ViewPager를 표현할 item.xml 생성
4. Adapter 구현
5. ViewPager를 사용할 액티비티에 2번에서 추가한 ViewPager를 호출하여 Adapter와 연결(Adapter에 데이터를 넘겨줘야 함)

## 1. dependencies 추가
```Groovy
    // 뷰페이저 추가
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
```

---

## 2. xml에 ViewPager2 속성 추가
```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/viewPager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
    
    </androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 3. item.xml 생성
ViewPager를 이미지로 구성할 것이기 때문에 ImageView 속성만 추가.
```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <ImageView
            android:id="@+id/imageView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:scaleType="fitXY"
            app:srcCompat="@drawable/intro1"/>
    
    </androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 4. Adapter 구현
```kotlin
    class ViewPagerAdapter(imageList: ArrayList<Int>): RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    
        var item = imageList
    
        inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var image: ImageView = itemView.findViewById(R.id.imageView)
        }
    
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
            return PagerViewHolder(view)
        }
    
        override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
            holder.image.setImageResource(item[position])
        }
    
        override fun getItemCount(): Int {
            return item.size
        }
    }
```

---

## 5. 액티비티에 ViewPager 선언하고 Adapter와 연결
```kotlin
    class MainActivity : AppCompatActivity() {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
    
            var viewPager = findViewById<ViewPager2>(R.id.viewPager)
            viewPager.adapter = ViewPagerAdapter(imageList())
        }
    
        private fun imageList(): ArrayList<Int>{
            return arrayListOf(R.drawable.intro1, R.drawable.intro2)
        }
    }
```

---

![ViewPager_gif](https://user-images.githubusercontent.com/52282493/160350273-ec1f9aa9-17c8-4844-a644-841411ae8411.gif)


