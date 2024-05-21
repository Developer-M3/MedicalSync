package com.mthree.medicalsync.analytics

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.extension.toFormattedDateString
import java.util.Date

private const val CONTROL_TIME = "control_time"
private const val CONTROL_END_DATE = "control_end_date"
private const val NOTIFICATION_TIME = "notification_time"

class ControlAnalyticsHelper(
    context: Context
) {
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    fun trackNotificationShown(control: Control) {
        val params = bundleOf(
            CONTROL_TIME to control.controlTime.toFormattedDateString(),
            CONTROL_END_DATE to control.endDate.toFormattedDateString(),
            NOTIFICATION_TIME to Date().toFormattedDateString()
        )
        logEvent(AnalyticsEvents.CONTROL_NOTIFICATION_SHOWN, params)
    }

    fun trackNotificationScheduled(control: Control) {
        val params = bundleOf(
            CONTROL_TIME to control.controlTime.toFormattedDateString(),
            CONTROL_END_DATE to control.endDate.toFormattedDateString(),
            NOTIFICATION_TIME to Date().toFormattedDateString()
        )
        logEvent(AnalyticsEvents.CONTROL_NOTIFICATION_SCHEDULED, params)
    }

    fun logEvent(eventName: String, params: Bundle? = null) {
        firebaseAnalytics.logEvent(eventName, params)
    }
}
