package com.mthree.medicalsync.domain.repository

import com.mthree.medicalsync.domain.model.Control
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ControlRepository {

    suspend fun insertControls(controls: List<Control>)

    suspend fun deleteControl(controls: Control)

    suspend fun updateControl(controls: Control)

    fun getAllControls(): Flow<List<Control>>

    fun getControlsForDate(date: Date): Flow<List<Control>>
}
