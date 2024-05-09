package com.mthree.medicalsync.feature.control.viewmodel

import com.mthree.medicalsync.domain.model.Control

data class ControlState(
    val greeting: String = "",
    val userName: String = "",
    val controls: List<Control> = emptyList()
)
