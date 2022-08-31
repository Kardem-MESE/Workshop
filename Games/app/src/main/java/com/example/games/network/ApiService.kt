package com.example.games.network

import com.example.games.model.FreeGames
import com.example.games.repo.FreeGameRepo
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import javax.inject.Inject

class ApiService @Inject constructor(private val client: HttpClient){
    suspend fun getFreeGames()
    : FreeGames = client.get("https://www.freetogame.com/api/games")
}