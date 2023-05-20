package com.github.guibrisson.tucano.data.repository

import com.github.guibrisson.tucano.data.testdoubles.TestTaskDao
import com.github.guibrisson.tucano.database.dao.TaskDao
import com.github.guibrisson.tucano.database.model.TaskEntity
import com.github.guibrisson.tucano.database.model.asExternalModel
import com.github.guibrisson.tucano.model.Task
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals


class TaskRepositoryTest {

    private lateinit var subject: TaskRepositoryImpl
    private lateinit var taskDao: TaskDao

    @Before
    fun setup() {
        taskDao = TestTaskDao()
        subject = TaskRepositoryImpl(taskDao)
    }

    @Test
    fun `creating a new task`() = runTest {
        val newTask = Task(
            id = "1",
            title = "task 1",
            description = null
        )

        subject.createNewTask(newTask)

        assertContains(
            array = subject.getAllTasks().first()!!.toTypedArray(),
            element = newTask
        )
    }

    @Test
    fun `getting task by id`() = runTest {
        val newTask = Task(
            id = "1",
            title = "task 1",
            description = null
        )

        subject.createNewTask(newTask)

        assertEquals(
            expected = taskDao.getTaskById(newTask.id)
                .first()!!.asExternalModel(),
            actual = subject.getTaskById(newTask.id).first()
        )
    }

    @Test
    fun `updating task`() = runTest {
        val newTask = Task(
            id = "1",
            title = "task 1",
            description = null
        )

        subject.createNewTask(newTask)

        assertEquals(
            expected = taskDao.getTaskById(newTask.id)
                .first()!!.asExternalModel(),
            actual = subject.getTaskById(newTask.id).first()
        )

        subject.updateTask(newTask.copy(description = "new description"))

        assertEquals(
            expected = taskDao.getTaskById(newTask.id)
                .first()!!.asExternalModel(),
            actual = subject.getTaskById(newTask.id).first()
        )
    }

    @Test
    fun `getting all tasks`() = runTest {
        assertEquals(
            expected = taskDao.getAllTasks()
                .first()
                .map(TaskEntity::asExternalModel),
            actual = subject.getAllTasks().first()
        )
    }

    @Test
    fun `deleting a task`() = runTest {
        val newTask = Task(
            id = "1",
            title = "task 1",
            description = null
        )

        subject.createNewTask(newTask)

        assertEquals(
            expected = taskDao.getTaskById(newTask.id)
                .first()!!.asExternalModel(),
            actual = subject.getTaskById(newTask.id).first()
        )

        subject.deleteTask(newTask)

        assertEquals(
            expected = taskDao.getAllTasks().first().map(TaskEntity::asExternalModel) - newTask,
            actual = subject.getAllTasks().first()
        )

    }

}
