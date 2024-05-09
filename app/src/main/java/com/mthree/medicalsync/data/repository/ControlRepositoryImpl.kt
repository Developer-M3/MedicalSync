package com.mthree.medicalsync.data.repository

import com.mthree.medicalsync.data.ControlDao
import com.mthree.medicalsync.data.mapper.toControl
import com.mthree.medicalsync.data.mapper.toControlEntity
import com.mthree.medicalsync.domain.model.Control
import com.mthree.medicalsync.domain.repository.ControlRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class ControlRepositoryImpl(
    private val dao: ControlDao
) : ControlRepository {

    override suspend fun insertControls(controls: List<Control>) {
        controls.map { it.toControlEntity() }.forEach {
            dao.insertControl(it)
        }
    }

    override suspend fun deleteControl(control: Control) {
        dao.deleteControl(control.toControlEntity())
    }

    override suspend fun updateControl(control: Control) {
        dao.updateControl(control.toControlEntity())
    }

    override fun getAllControls(): Flow<List<Control>> {
        return dao.getAllControls().map { entities ->
            entities.map { it.toControl() }
        }
    }

    override fun getControlsForDate(date: Date): Flow<List<Control>> {
        return dao.getControlsForDate(
            date = date
        ).map { entities ->
            entities.map { it.toControl() }
        }
    }
}
