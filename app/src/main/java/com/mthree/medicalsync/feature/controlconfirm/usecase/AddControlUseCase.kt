package com.mthree.medicalsync.feature.controlconfirm.usecase

import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.domain.repository.ControlRepository
import javax.inject.Inject

class AddControlUseCase @Inject constructor(
    private val repository: ControlRepository
) {
    suspend fun addControl(controls: List<Control>) {
        repository.insertControls(controls)
    }
}
