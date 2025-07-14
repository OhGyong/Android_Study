package com.study.calendarbykizitonwose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun MainScreen(sampleList: List<SampleData> = listOf()) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(2) }
    val endMonth = remember { currentMonth.plusMonths(0) }
    val daysOfWeek = remember { daysOfWeek() }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    // 60일 전과 오늘 이후 날짜 사용 금지
    val today = LocalDate.now()
    val agoDate = today.minusDays(60)

    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    Column {
        YearMonthTitle(calendarState)
        HorizontalCalendar(
            modifier = Modifier.padding(top = 10.dp),
            state = calendarState,
            dayContent = {
                Day(
                    day = it,
                    sampleList = sampleList,
                    isSelected = selectedDate == it.date,
                    useDate = !it.date.isBefore(agoDate) && !it.date.isAfter(today),
                    onClick = { calendarDay ->
                        selectedDate = if(selectedDate == it.date) null else it.date
                    }
                )
            },
            monthHeader = { month ->
                val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
                Week(daysOfWeek = daysOfWeek)
            }
        )
    }
}

@Composable
fun YearMonthTitle(calendarState: CalendarState) {
    val yearMoth = calendarState.firstVisibleMonth.yearMonth.toString()
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = yearMoth,
        style = CalendarTheme.titleLarge,
        color = colorResource(R.color.black)
    )
}

@Composable
fun Week(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = colorResource(when(dayOfWeek) {
                    DayOfWeek.SUNDAY -> {
                        R.color.colorRed
                    }
                    DayOfWeek.SATURDAY -> {
                        R.color.colorBlue
                    }
                    else -> {
                        R.color.colorDarkGray
                    }
                }),
                style = CalendarTheme.titleMedium
            )
        }
    }
}

@Composable
fun Day(
    day: CalendarDay,
    sampleList: List<SampleData>,
    isSelected: Boolean,
    useDate: Boolean,
    onClick: (CalendarDay) -> Unit
) {
    val count = sampleList.find { it.date == day.date.toString() }?.total?:0

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                color = if (isSelected) colorResource(R.color.colorPurple) else Color.Transparent
            )
            .clickable(
                enabled = useDate,
                onClick = { onClick(day) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = day.date.dayOfMonth.toString(),
                color =
                    if(useDate) {
                        if(isSelected) colorResource(R.color.white)
                        else colorResource(R.color.black)
                    } else {
                        colorResource(R.color.colorGray)
                    },
                style = CalendarTheme.bodyMedium
            )

            if(useDate) {
                Text(
                    text = "${count}건",
                    color =
                        if(isSelected) colorResource(R.color.white)
                        else colorResource(R.color.colorPurple),
                    style = CalendarTheme.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun MainScreenPreview() {
    MainScreen()
}