package com.example.games.repo

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.example.games.model.FreeGames
import com.example.games.model.FreeGamesItem
import com.example.games.network.ApiService
import com.example.games.network.KtorClient
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.scopes.ActivityScoped
import io.ktor.client.request.*
import javax.inject.Inject
import kotlin.Result.Companion.success

@ActivityScoped
class FreeGameRepo  @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getFreeGames(): FreeGames {
        return KtorClient.httpClient.use {
            apiService.getFreeGames()
        }
    }
}
