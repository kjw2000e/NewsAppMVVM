package com.jiwon.newsappmvvm.di

import com.jiwon.newsappmvvm.repository.NewsRepository
import com.jiwon.newsappmvvm.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): NewsRepository
}