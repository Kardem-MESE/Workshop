package com.example.games.repository

import javax.inject.Inject

interface ICategoryRepository{
    fun getAllCategories(): String
}

class CategoryRepository @Inject constructor(

): ICategoryRepository{
    override fun getAllCategories(): String {
        return "KKKK"
    }
}

