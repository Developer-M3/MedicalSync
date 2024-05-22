package com.mthree.medicalsync.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ControlEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val dosage: String,
    val recurrence: String,
    val endDate: Date,
    val controlTime: Date = Date(),
    val controlTaken: Boolean,
)
