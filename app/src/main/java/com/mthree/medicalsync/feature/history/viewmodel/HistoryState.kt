package com.mthree.medicalsync.feature.history.viewmodel

import com.mthree.medicalsync.domain.model.Medication

data class HistoryState(
    val medications: List<Medication> = emptyList()
)
