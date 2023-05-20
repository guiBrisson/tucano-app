package com.github.guibrisson.tucano.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.github.guibrisson.tucano.database.AppDatabase
import com.github.guibrisson.tucano.database.model.TaskEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class TaskDaoTest {

    private lateinit var taskDao: TaskDao
    private lateinit var db: AppDatabase
    private val tasks = listOf(
        TaskEntity(id = "1", title = "item 1", description = null),
        TaskEntity(id = "2", title = "item 2", description = null),
        TaskEntity(id = "3", title = "item 3", description = null),
        TaskEntity(id = "4", title = "item 4", description = null),
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()

        taskDao = db.taskDao()
    }

    @Test
    fun get_all_tasks() = runTest {
        taskDao.upsertTask(task = tasks.toTypedArray())

        assertEquals(
            expected = tasks.toSet(),
            actual = taskDao.getAllTasks().first().toSet()
        )
    }

    @Test
    fun get_task_by_id() = runTest {
        taskDao.upsertTask(task = tasks.toTypedArray())
        val task = tasks.first()

        assertEquals(
            expected = task,
            actual = taskDao.getTaskById(task.id).first()
        )
    }

    @Test
    fun delete_tasks() = runTest {
        taskDao.upsertTask(task = tasks.toTypedArray())

        val (toDelete, toKeep) = tasks.partition { it.id.toInt() % 2 == 0 }

        taskDao.delete(task = toDelete.toTypedArray())

        assertEquals(
            expected = toKeep.toSet(),
            actual = taskDao.getAllTasks().first().toSet()
        )
    }
}
