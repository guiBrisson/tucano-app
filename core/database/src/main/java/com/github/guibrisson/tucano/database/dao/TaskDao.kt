package com.github.guibrisson.tucano.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.github.guibrisson.tucano.database.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: String): Flow<Task>

    @Upsert
    fun upsertTask(vararg task: Task)

    @Insert
    fun insertTask(vararg task: Task)

    @Delete
    fun delete(vararg task: Task)
}
