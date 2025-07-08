package com.study.calendarbykizitonwose

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view: View): ViewContainer(view) {
    val containerView = view.findViewById<ConstraintLayout>(R.id.cl_container)
    val textView = view.findViewById<TextView>(R.id.calendarDayText)
}