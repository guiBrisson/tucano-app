package com.github.guibrisson.tucano.data.repository

import com.github.guibrisson.tucano.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun createNewTask(task: Task)
    suspend fun updateTask(task: Task)
    fun getAllTasks(): Flow<List<Task>?>
    fun getTaskById(id: String): Flow<Task?>
    suspend fun deleteTask(vararg task: Task)
}