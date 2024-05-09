package com.mthree.medicalsync.feature.control.usecase

import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.domain.repository.ControlRepository
import javax.inject.Inject

class UpdateControlUseCase @Inject constructor(
    private val repository: ControlRepository
) {

    suspend fun updateControl(control: Control) {
        return repository.updateControl(control)
    }
}
