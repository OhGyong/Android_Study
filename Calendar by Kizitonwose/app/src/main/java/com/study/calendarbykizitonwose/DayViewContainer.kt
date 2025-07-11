package com.study.calendarbykizitonwose

import android.view.View
import com.kizitonwose.calendar.view.ViewContainer
import com.study.calendarbykizitonwose.databinding.CalendarDayLayoutBinding

class DayViewContainer(view: View): ViewContainer(view) {
    val binding = CalendarDayLayoutBinding.bind(view)
    val containerView = binding.clContainer
    val dayView = binding.calendarDayText
    val countView = binding.tvCount
}