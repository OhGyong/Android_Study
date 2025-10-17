package com.study.workmanagerwithalarmmanager.core

import com.study.workmanagerwithalarmmanager.data.SampleRepositoryImpl
import com.study.workmanagerwithalarmmanager.domain.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSampleRepository(repository: SampleRepositoryImpl): SampleRepository
}