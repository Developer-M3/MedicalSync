package com.mthree.medicalsync.feature.addcontrol.viewmodel

import androidx.lifecycle.ViewModel
import com.mthree.medicalsync.analytics.AnalyticsHelper
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.feature.addcontrol.model.CalendarInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddControlViewModel @Inject constructor(
    private val analyticsHelper: AnalyticsHelper
) : ViewModel() {

    fun createControls(
        name: String,
        description: String,
        dosage: String,
        recurrence: String,
        endDate: Date,
        controlTimes: List<CalendarInformation>,
        startDate: Date = Date()
    ): List<Control> {

        // Determine the recurrence interval based on the selected recurrence
        val interval = when (recurrence) {
            "Daily" -> 1
            "Weekly" -> 7
            "Monthly" -> 30
            else -> throw IllegalArgumentException("Invalid recurrence: $recurrence")
        }

        val oneDayInMillis = 86400 * 1000 // Number of milliseconds in one day
        val numOccurrences = ((endDate.time + oneDayInMillis - startDate.time) / (interval * oneDayInMillis)).toInt() + 1

        // Create a Control object for each occurrence and add it to a list
        val controls = mutableListOf<Control>()
        val calendar = Calendar.getInstance()
        calendar.time = startDate
        for (i in 0 until numOccurrences) {
            for (controlTime in controlTimes) {
                // TODO: Generate id automatically.
                val control = Control(
                    id = 0,
                    name = name,
                    description = description,
                    dosage = dosage,
                    recurrence = recurrence,
                    endDate = endDate,
                    controlTaken = false,
                    controlTime = getControlTime(controlTime, calendar)
                )
                controls.add(control)
            }

            // Increment the date based on the recurrence interval
            calendar.add(Calendar.DAY_OF_YEAR, interval)
        }

        return controls
    }

    private fun getControlTime(controlTime: CalendarInformation, calendar: Calendar): Date {
        calendar.set(Calendar.HOUR_OF_DAY, controlTime.dateInformation.hour)
        calendar.set(Calendar.MINUTE, controlTime.dateInformation.minute)
        return calendar.time
    }

    fun logEvent(eventName: String) {
        analyticsHelper.logEvent(eventName = eventName)
    }
}
