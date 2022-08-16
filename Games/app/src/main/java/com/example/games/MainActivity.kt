package com.example.games

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.games.model.FreeGamesItem
import com.example.games.ui.theme.GamesTheme
import com.example.games.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        setContent {
            GamesTheme {
                GameApp(viewModel = viewModel)
                BottomNav()
            }
        }
    }
}

@Composable
fun GameApp(viewModel: GameViewModel) {
    Scaffold(
        content = {
            FreeGames(viewModel = viewModel)
        }
    )
}

@Composable
fun BottomNav(){
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController)
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(text = "GAMES", fontSize = 18.sp)},
        backgroundColor = Color.Gray,
        contentColor = Color.Black
    )
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        NavigationItems.Profil,
        NavigationItems.Home
    )
    BottomNavigation(
        backgroundColor = Color.Gray,
        contentColor = Color.Black
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach{items ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = items.Icon), contentDescription = items.Title)},
                label = { Text(text = items.Title)},
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == items.route ,
                onClick = {
                    navController.navigate(items.route){
                        navController.graph.startDestinationRoute?.let{ route ->
                            popUpTo(route = route){
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun ProfilScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "You're Welcome!!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Image(
            painter = painterResource(id = R.drawable.hosgeldiniz),
            contentDescription = "resim",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController, startDestination = NavigationItems.Home.route){
        composable(NavigationItems.Home.route){
            GameApp(viewModel = GameViewModel())
        }
        composable(NavigationItems.Profil.route){
            ProfilScreen()
        }
    }
}





