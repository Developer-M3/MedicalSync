package com.mthree.medicalsync.data.mapper

import com.mthree.medicalsync.data.entity.MedicationEntity
import com.mthree.medicalsync.domain.model.Medication

fun MedicationEntity.toMedication(): Medication {
    return Medication(
        id = id,
        name = name,
        dosage = dosage,
        recurrence = recurrence,
        endDate = endDate,
        medicationTime = medicationTime,
        medicationTaken = medicationTaken
    )
}

fun Medication.toMedicationEntity(): MedicationEntity {
    return MedicationEntity(
        id = id ?: 0L,
        name = name,
        dosage = dosage,
        recurrence = recurrence,
        endDate = endDate,
        medicationTime = medicationTime,
        medicationTaken = medicationTaken
    )
}
