package com.github.guibrisson.tucano.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.guibrisson.tucano.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String?
)

fun TaskEntity.asExternalModel() = Task(
    id = id,
    title = title,
    description = description
)
