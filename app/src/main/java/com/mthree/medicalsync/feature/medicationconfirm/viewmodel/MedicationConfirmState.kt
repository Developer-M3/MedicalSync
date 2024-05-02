package com.mthree.medicalsync.feature.medicationconfirm.viewmodel

import com.mthree.medicalsync.domain.model.Medication

data class MedicationConfirmState(
    val medications: List<Medication>
)
