package com.mthree.medicalsync.feature.control.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mthree.medicalsync.analytics.AnalyticsHelper
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.feature.control.usecase.GetControlsUseCase
import com.mthree.medicalsync.feature.control.usecase.UpdateControlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ControlViewModel @Inject constructor(
    private val getControlsUseCase: GetControlsUseCase,
    private val updateControlUseCase: UpdateControlUseCase,
    private val analyticsHelper: AnalyticsHelper
) : ViewModel() {

    var state by mutableStateOf(ControlState())
        private set

    init {
        loadControls()
    }

    fun getUserName() {
        state = state.copy(userName = "Kathryn")
        // TODO: Get user name from DB
    }

    fun getGreeting() {
        state = state.copy(greeting = "Greeting")
        // TODO: Get greeting by checking system time
    }

    fun loadControls() {
        viewModelScope.launch {
            getControlsUseCase.getControls().onEach { controlList ->
                state = state.copy(
                    controls = controlList
                )
            }.launchIn(viewModelScope)
        }
    }

    fun takeControl(control: Control) {
        viewModelScope.launch {
            updateControlUseCase.updateControl(control)
        }
    }

    fun getUserPlan() {
        // TODO: Get user plan
    }

    fun logEvent(eventName: String) {
        analyticsHelper.logEvent(eventName = eventName)
    }
}
