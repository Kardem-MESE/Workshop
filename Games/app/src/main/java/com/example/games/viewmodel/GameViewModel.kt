package com.example.games.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.model.FreeGames
import com.example.games.model.FreeGamesItem
import com.example.games.repo.FreeGameRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repo: FreeGameRepo
) : ViewModel(){

    val freeGamesFlow = MutableStateFlow<FreeGames?>(null)
    init {
        viewModelScope.launch {
            kotlin.runCatching {
                repo.getFreeGames()
            }.onSuccess {
                freeGamesFlow.value = it
            }.onFailure {
                freeGamesFlow.value = null
            }
        }
    }
}



