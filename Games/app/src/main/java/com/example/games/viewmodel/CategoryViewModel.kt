package com.example.games.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.games.usecase.IGetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
        useCase: IGetCategoriesUseCase
) : ViewModel(){
    init {
        val a = useCase()
        Log.d("Kardem",a)
    }
}