package com.github.guibrisson.tucano.data.di

import com.github.guibrisson.tucano.data.repository.TaskRepository
import com.github.guibrisson.tucano.data.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsTaskRepository(
        taskRepository: TaskRepositoryImpl
    ): TaskRepository

}