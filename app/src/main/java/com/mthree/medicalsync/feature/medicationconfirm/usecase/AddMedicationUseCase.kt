package com.mthree.medicalsync.feature.medicationconfirm.usecase

import com.mthree.medicalsync.domain.model.Medication
import com.mthree.medicalsync.domain.repository.MedicationRepository
import javax.inject.Inject

class AddMedicationUseCase @Inject constructor(
    private val repository: MedicationRepository
) {
    suspend fun addMedication(medications: List<Medication>) {
        repository.insertMedications(medications)
    }
}
