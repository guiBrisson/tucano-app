package com.github.guibrisson.tucano.data.testdoubles

import com.github.guibrisson.tucano.database.dao.TaskDao
import com.github.guibrisson.tucano.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class TestTaskDao: TaskDao {

    private var entitiesStateFlow = MutableStateFlow(
        emptyList<TaskEntity>()
    )

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return entitiesStateFlow
    }

    override fun getTaskById(id: String): Flow<TaskEntity?> {
        return entitiesStateFlow.map { tasks ->
            tasks.find { it.id == id }
        }
    }

    override suspend fun upsertTask(vararg task: TaskEntity) {
        entitiesStateFlow.update { oldValues ->
            (task.toList() + oldValues).distinctBy(TaskEntity::id)
        }
    }

    override suspend fun delete(vararg task: TaskEntity) {
        task.forEach { task1 ->
            entitiesStateFlow.update { entities ->
                entities.filterNot { it == task1 }
            }
        }
    }

}
