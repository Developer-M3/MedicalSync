package com.mthree.medicalsync.feature.home.viewmodel

import com.mthree.medicalsync.domain.model.Medication

data class HomeState(
    val greeting: String = "",
    val userName: String = "",
    val medications: List<Medication> = emptyList()
)
