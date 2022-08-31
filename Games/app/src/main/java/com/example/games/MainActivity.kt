package com.example.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.games.repo.FreeGameRepo
import com.example.games.ui.theme.GamesTheme
import com.example.games.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    @Inject lateinit var repo: FreeGameRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepVisibleCondition{
                viewModel.isLoading.value
            }
        }
        val viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        setContent {
            GamesTheme {
                GameApp(viewModel = hiltViewModel())
                BottomNav()
            }
        }
    }
}

@Composable
fun GameApp(viewModel: GameViewModel = hiltViewModel()) {
    Scaffold(
        content = {
           FreeGames(viewModel = hiltViewModel() )
        }
    )
}







