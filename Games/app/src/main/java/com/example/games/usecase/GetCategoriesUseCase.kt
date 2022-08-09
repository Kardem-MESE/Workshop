package com.example.games.usecase

import com.example.games.repository.ICategoryRepository
import javax.inject.Inject

interface IGetCategoriesUseCase{
    operator fun invoke(): String
}

class GetCategoriesUseCase @Inject constructor(
    val repo: ICategoryRepository
) : IGetCategoriesUseCase {
    override fun invoke(): String {
        return repo.getAllCategories()
    }
}