package com.mthree.medicalsync.data.mapper

import com.mthree.medicalsync.data.entity.ControlEntity
import com.mthree.medicalsync.domain.model.Control

fun ControlEntity.toControl(): Control {
    return Control(
        id = id,
        name = name,
        description = description,
        dosage = dosage,
        recurrence = recurrence,
        endDate = endDate,
        controlTime = controlTime,
        controlTaken = controlTaken
    )
}

fun Control.toControlEntity(): ControlEntity {
    return ControlEntity(
        id = id ?: 0L,
        name = name,
        description = description,
        dosage = dosage,
        recurrence = recurrence,
        endDate = endDate,
        controlTime = controlTime,
        controlTaken = controlTaken
    )
}
