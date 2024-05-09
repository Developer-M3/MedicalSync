package com.mthree.medicalsync.feature.control.usecase

import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.domain.repository.ControlRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetControlsUseCase @Inject constructor(
    private val repository: ControlRepository
) {

    suspend fun getAllControls(): Flow<List<Control>> {
        return repository.getAllControls()
    }
}
