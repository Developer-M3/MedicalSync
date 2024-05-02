package com.mthree.medicalsync.data.repository

import com.mthree.medicalsync.data.MedicationDao
import com.mthree.medicalsync.data.mapper.toMedication
import com.mthree.medicalsync.data.mapper.toMedicationEntity
import com.mthree.medicalsync.domain.model.Medication
import com.mthree.medicalsync.domain.repository.MedicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class MedicationRepositoryImpl(
    private val dao: MedicationDao
) : MedicationRepository {

    override suspend fun insertMedications(medications: List<Medication>) {
        medications.map { it.toMedicationEntity() }.forEach {
            dao.insertMedication(it)
        }
    }

    override suspend fun deleteMedication(medication: Medication) {
        dao.deleteMedication(medication.toMedicationEntity())
    }

    override suspend fun updateMedication(medication: Medication) {
        dao.updateMedication(medication.toMedicationEntity())
    }

    override fun getAllMedications(): Flow<List<Medication>> {
        return dao.getAllMedications().map { entities ->
            entities.map { it.toMedication() }
        }
    }

    override fun getMedicationsForDate(date: Date): Flow<List<Medication>> {
        return dao.getMedicationsForDate(
            date = date
        ).map { entities ->
            entities.map { it.toMedication() }
        }
    }
}
