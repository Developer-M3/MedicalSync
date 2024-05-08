package com.mthree.medicalsync.feature.control.usecase

import com.mthree.medicalsync.domain.model.Medication
import com.mthree.medicalsync.domain.repository.MedicationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicationsUseCase @Inject constructor(
    private val repository: MedicationRepository
) {

    suspend fun getMedications(): Flow<List<Medication>> {
        return repository.getAllMedications()
    }
}
