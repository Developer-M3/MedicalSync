package com.mthree.medicalsync.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mthree.medicalsync.data.entity.MedicationEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface MedicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medicationEntity: MedicationEntity): Long

    @Delete
    suspend fun deleteMedication(medicationEntity: MedicationEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMedication(medicationEntity: MedicationEntity)

    @Query(
        """
            SELECT *
            FROM medicationentity
        """
    )
    fun getAllMedications(): Flow<List<MedicationEntity>>

    @Query(
        """
            SELECT *
            FROM medicationentity
            WHERE endDate > :date
        """
    )
    fun getMedicationsForDate(date: Date): Flow<List<MedicationEntity>>
}
