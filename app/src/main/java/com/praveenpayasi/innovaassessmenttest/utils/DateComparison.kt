package com.praveenpayasi.innovaassessmenttest.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateComparison {

    fun isTodaysAstronomy(storedDateStr: String): Boolean {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val storedDate: Date
        try {
            storedDate = dateFormat.parse(storedDateStr) ?: return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        val storedCalendar = Calendar.getInstance().apply { time = storedDate }
        val todayCalendar = Calendar.getInstance()

        // Set the time components to zero for a full-day comparison
        storedCalendar.set(Calendar.HOUR_OF_DAY, 0)
        storedCalendar.set(Calendar.MINUTE, 0)
        storedCalendar.set(Calendar.SECOND, 0)
        storedCalendar.set(Calendar.MILLISECOND, 0)

        todayCalendar.set(Calendar.HOUR_OF_DAY, 0)
        todayCalendar.set(Calendar.MINUTE, 0)
        todayCalendar.set(Calendar.SECOND, 0)
        todayCalendar.set(Calendar.MILLISECOND, 0)


        return storedCalendar.timeInMillis == todayCalendar.timeInMillis
    }

}