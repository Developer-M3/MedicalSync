package com.mthree.medicalsync

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mthree.medicalsync.analytics.AnalyticsEvents
import com.mthree.medicalsync.analytics.AnalyticsHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicalSync(analyticsHelper = analyticsHelper)
        }
        parseIntent(intent)
    }

    private fun parseIntent(intent: Intent?) {
        val isMedicationNotification = intent?.getBooleanExtra(MEDICATION_NOTIFICATION, false) ?: false
        if (isMedicationNotification) {
            analyticsHelper.logEvent(AnalyticsEvents.REMINDER_NOTIFICATION_CLICKED)
        }
    }
}
