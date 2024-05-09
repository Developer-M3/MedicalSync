package com.mthree.medicalsync.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mthree.medicalsync.data.entity.ControlEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ControlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertControl(controlEntity: ControlEntity): Long

    @Delete
    suspend fun deleteControl(ControlEntity: ControlEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateControl(ControlEntity: ControlEntity)

    @Query(
        """
            SELECT *
            FROM controlentity
        """
    )
    fun getAllControls(): Flow<List<ControlEntity>>

    @Query(
        """
            SELECT *
            FROM controlentity
            WHERE endDate > :date
        """
    )
    fun getControlsForDate(date: Date): Flow<List<ControlEntity>>
}
