package com.example.games.di

import com.example.games.repository.CategoryRepository
import com.example.games.repository.ICategoryRepository
import com.example.games.usecase.GetCategoriesUseCase
import com.example.games.usecase.IGetCategoriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt{

        @Binds
        @Singleton
        fun provideCategoryRepository(repo: CategoryRepository) : ICategoryRepository

        @Binds
        @Singleton
        fun provideCategoryUseCase(uc: GetCategoriesUseCase) : IGetCategoriesUseCase
    }

}