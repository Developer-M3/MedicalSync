package com.mthree.medicalsync

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mthree.medicalsync.analytics.ControlAnalyticsHelper
import com.mthree.medicalsync.domain.model.Control

class ControlNotificationService(
    private val context: Context
) {

    fun scheduleNotification(control: Control, analyticsHelper: ControlAnalyticsHelper) {
        val intent = Intent(context, ControlNotificationReceiver::class.java)
        intent.putExtra(CONTROL_INTENT, control)

        // TODO: Replace control.hashCode() with control.id when control.id is fixed.
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            control.hashCode(),
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val alarmService = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = control.controlTime.time

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                alarmService.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    time,
                    pendingIntent
                )
            } catch (exception: SecurityException) {
                FirebaseCrashlytics.getInstance().recordException(exception)
            }
        }

        analyticsHelper.trackNotificationScheduled(control)
    }

    companion object {
        const val CONTROL_CHANNEL_ID = "control_channel"
    }
}
