package com.mthree.medicalsync.feature.controlconfirm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mthree.medicalsync.ControlNotificationService
import com.mthree.medicalsync.MedicationNotificationService
import com.mthree.medicalsync.analytics.AnalyticsHelper
import com.mthree.medicalsync.analytics.ControlAnalyticsHelper
import com.mthree.medicalsync.feature.controlconfirm.usecase.AddControlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ControlConfirmViewModel @Inject constructor(
    private val addControlUseCase: AddControlUseCase,
    private val analyticsHelper: ControlAnalyticsHelper
) : ViewModel() {

    private val _isControlSaved = MutableSharedFlow<Unit>()
    val isControlSaved = _isControlSaved.asSharedFlow()

    fun addControl(context: Context, state: ControlConfirmState) {
        viewModelScope.launch {
            val controls = state.controls
            val controlAdded = addControlUseCase.addControl(controls)

            for (control in controls) {
                val service = ControlNotificationService(context)
                service.scheduleNotification(
                    control = control,
                    analyticsHelper = analyticsHelper
                )
            }

            _isControlSaved.emit(controlAdded)
        }
    }

    fun logEvent(eventName: String) {
        analyticsHelper.logEvent(eventName = eventName)
    }
}
