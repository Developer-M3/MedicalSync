package com.mthree.medicalsync.domain.repository

import com.mthree.medicalsync.domain.model.Medication
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface MedicationRepository {

    suspend fun insertMedications(medications: List<Medication>)

    suspend fun deleteMedication(medication: Medication)

    suspend fun updateMedication(medication: Medication)

    fun getAllMedications(): Flow<List<Medication>>

    fun getMedicationsForDate(date: Date): Flow<List<Medication>>
}
