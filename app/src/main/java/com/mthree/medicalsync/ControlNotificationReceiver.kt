package com.mthree.medicalsync

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.mthree.medicalsync.analytics.ControlAnalyticsHelper
import com.mthree.medicalsync.domain.model.Control
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val CONTROL_INTENT = "control_intent"
const val CONTROL_NOTIFICATION = "control_notification"

@AndroidEntryPoint
class ControlNotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var analyticsHelper: ControlAnalyticsHelper

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.getParcelableExtra<Control>(CONTROL_INTENT)?.let { control ->
                showNotification(it, control)
            }
        }
    }

    private fun showNotification(context: Context, control: Control) {
        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.putExtra(CONTROL_NOTIFICATION, true)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val receiverIntent = Intent(context, NotificationActionReceiver::class.java)
        /*val takenPendingIntent = PendingIntent.getBroadcast(
            context,
            2,
            receiverIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )*/

        // TODO: Add action.
        val notification = NotificationCompat.Builder(
            context,
            ControlNotificationService.CONTROL_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_pill)
            .setContentTitle(context.getString(R.string.control_reminder))
            .setContentText(context.getString(R.string.control_reminder_time, control.name))
            .setContentIntent(activityPendingIntent)
            /*.addAction(
                R.drawable.doctor,
                "Take now",
                takenPendingIntent)*/
            .build()

        // TODO: Use control id as notification id.
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(control.hashCode(), notification)

        analyticsHelper.trackNotificationShown(control)
    }
}
