package com.github.guibrisson.tucano.database.di

import com.github.guibrisson.tucano.database.AppDatabase
import com.github.guibrisson.tucano.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesTaskDao(
        database: AppDatabase
    ): TaskDao = database.taskDao()
}