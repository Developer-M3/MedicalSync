package com.mthree.medicalsync.feature.home.usecase

import com.mthree.medicalsync.domain.model.Medication
import com.mthree.medicalsync.domain.repository.MedicationRepository
import javax.inject.Inject

class UpdateMedicationUseCase @Inject constructor(
    private val repository: MedicationRepository
) {

    suspend fun updateMedication(medication: Medication) {
        return repository.updateMedication(medication)
    }
}
