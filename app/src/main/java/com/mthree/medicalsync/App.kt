package com.mthree.medicalsync

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

// DAVIN WAHYU WARDANA
// 6706223003
// D3IF-4603

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Medication Notification Channel
            val medicationChannel = NotificationChannel(
                MedicationNotificationService.MEDICATION_CHANNEL_ID,
                getString(R.string.medication_reminder),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = getString(R.string.notifications_for_medication_reminder)
            }

            // Control Notification Channel
            val controlChannel = NotificationChannel(
                ControlNotificationService.CONTROL_CHANNEL_ID,
                getString(R.string.control_reminder),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = getString(R.string.notifications_for_control_reminder)
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(medicationChannel)
            notificationManager.createNotificationChannel(controlChannel)
        }
    }
}