package com.mthree.medicalsync.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.mthree.medicalsync.data.entity.ControlEntity

@Database(
    entities = [ControlEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = ControlDatabase.AutoMigration::class)
    ]
)
@TypeConverters(Converters::class)
abstract class ControlDatabase : RoomDatabase() {

    abstract val dao: ControlDao
    @DeleteColumn(tableName = "ControlEntity", columnName = "timesOfDay")
    @RenameColumn(tableName = "ControlEntity", fromColumnName = "date", toColumnName = "controlTime")
    class AutoMigration : AutoMigrationSpec
}
