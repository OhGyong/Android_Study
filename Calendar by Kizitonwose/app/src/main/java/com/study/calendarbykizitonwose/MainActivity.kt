package com.study.calendarbykizitonwose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.study.calendarbykizitonwose.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.random.Random

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var selectedDate: LocalDate? = null
    private lateinit var today: LocalDate
    private var sampleList = listOf<SampleData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSampleData()
        setCalendar()
        setListener()
    }

    private fun setSampleData() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        today = LocalDate.now()

        sampleList = (0 until 50).map { i ->
            SampleData(
                total = Random.nextInt(10, 100),
                date = today.minusDays(i.toLong()).format(formatter)
            )
        }
    }

    private fun setCalendar() {
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(2)
        val endMonth = currentMonth.plusMonths(0)
        val daysOfWeek = daysOfWeek()
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)
    }

    private fun setListener() {
        binding.calendarView.monthScrollListener = { month ->
            binding.tvTitleDate.text = month.yearMonth.toString()
        }

        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                val containerView = container.containerView
                val dayView = container.dayView
                val countView = container.countView

                // 60일 전과 오늘 이후 날짜 사용 금지
                val agoDate = today.minusDays(60)
                val useDate = !data.date.isBefore(agoDate) && !data.date.isAfter(today)

                dayView.text = data.date.dayOfMonth.toString()

                if(useDate) {
                    containerView.visibility = View.VISIBLE

                    val count = sampleList.find { it.date == data.date.toString() }?.total?:0
                    countView.text = "${count}건"

                    if (data.date == selectedDate) {
                        dayView.setTextColor(getColor(R.color.white))
                        countView.setTextColor(getColor(R.color.white))
                        containerView.setBackgroundResource(R.drawable.selection_background)
                    } else {
                        dayView.setTextColor(getColor(R.color.black))
                        countView.setTextColor(getColor(R.color.colorPurple))
                        containerView.background = null
                    }

                    containerView.setOnClickListener {
                        val currentSelection = selectedDate
                        if (currentSelection == data.date) {
                            selectedDate = null
                            binding.calendarView.notifyDateChanged(currentSelection)
                        } else {
                            selectedDate = data.date
                            binding.calendarView.notifyDateChanged(data.date)
                            if (currentSelection != null) {
                                binding.calendarView.notifyDateChanged(currentSelection)
                            }
                        }
                    }
                } else {
                    dayView.setTextColor(getColor(R.color.colorGray))
                    countView.setTextColor(getColor(R.color.colorGray))
                }
            }
        }

        binding.calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            val daysOfWeek = daysOfWeek()

            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                if (container.titlesContainer.tag == null) {
                    container.titlesContainer.tag = data.yearMonth
                    container.titlesContainer.children.map { it as TextView }
                        .forEachIndexed { index, textView ->
                            val dayOfWeek = daysOfWeek[index]
                            val title = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                            if (title == "일") {
                                textView.setTextColor(getColor(R.color.colorRed))
                            } else if (title == "토") {
                                textView.setTextColor(getColor(R.color.colorBlue))
                            }

                            textView.text = title
                        }
                }
            }
        }
    }
}