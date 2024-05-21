package com.mthree.medicalsync.feature.controldetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mthree.medicalsync.analytics.AnalyticsHelper
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.feature.control.usecase.UpdateControlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ControlDetailViewModel @Inject constructor(
    private val updateControlUseCase: UpdateControlUseCase,
    private val analyticsHelper: AnalyticsHelper
) : ViewModel() {

    fun updateControl(control: Control, isControlTaken: Boolean) {
        viewModelScope.launch {
            updateControlUseCase.updateControl(control.copy(controlTaken = isControlTaken))
        }
    }

    fun logEvent(eventName: String) {
        analyticsHelper.logEvent(eventName = eventName)
    }
}
