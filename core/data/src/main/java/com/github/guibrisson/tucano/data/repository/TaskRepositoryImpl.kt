package com.github.guibrisson.tucano.data.repository

import com.github.guibrisson.tucano.database.dao.TaskDao
import com.github.guibrisson.tucano.database.model.TaskEntity
import com.github.guibrisson.tucano.database.model.asExternalModel
import com.github.guibrisson.tucano.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun createNewTask(task: Task) {
        taskDao.upsertTask(task.mapAsEntity())
    }

    override fun getTaskById(id: String): Flow<Task?> {
        return taskDao.getTaskById(id).map { it?.asExternalModel() }
    }

    override suspend fun updateTask(task: Task) {
        taskDao.upsertTask(task.mapAsEntity())
    }

    override fun getAllTasks(): Flow<List<Task>?> {
        return taskDao.getAllTasks().map { entityList -> entityList.map { it.asExternalModel() } }
    }

    override suspend fun deleteTask(vararg task: Task) {
        taskDao.delete(*task.map { it.mapAsEntity() }.toTypedArray())
    }

    private fun Task.mapAsEntity() = TaskEntity(
        id = id,
        title = title,
        description = description
    )
}