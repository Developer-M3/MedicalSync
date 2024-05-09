package com.mthree.medicalsync.feature.control.viewmodel

import com.mthree.medicalsync.domain.model.Medication

data class ControlState(
    val greeting: String = "",
    val userName: String = "",
    val medications: List<Medication> = emptyList()
)
