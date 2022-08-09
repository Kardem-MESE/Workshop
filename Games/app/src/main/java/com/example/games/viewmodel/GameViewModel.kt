package com.example.games.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.model.FreeGames
import com.example.games.repo.FreeGameRepo
import com.example.games.usecase.IGetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class GameViewModel() : ViewModel() {

    val freeGamesFlow = MutableStateFlow<FreeGames?>(null)
    init {
        viewModelScope.launch {
            kotlin.runCatching {
                FreeGameRepo.getFreeGames()
            }.onSuccess {
                freeGamesFlow.value = it
            }.onFailure {
                freeGamesFlow.value = null
            }
        }
    }
}



