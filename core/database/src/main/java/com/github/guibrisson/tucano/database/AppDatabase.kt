package com.github.guibrisson.tucano.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.guibrisson.tucano.database.dao.TaskDao
import com.github.guibrisson.tucano.database.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
