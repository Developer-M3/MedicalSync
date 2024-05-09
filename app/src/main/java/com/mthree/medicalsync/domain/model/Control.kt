package com.mthree.medicalsync.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Control(
    val id: Long?,
    val name: String,
    val dosage: Int,
    val recurrence: String,
    val endDate: Date,
    val controlTaken: Boolean,
    val controlTime: Date
) : Parcelable
