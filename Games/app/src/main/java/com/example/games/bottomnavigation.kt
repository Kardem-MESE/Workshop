package com.example.games

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.games.viewmodel.GameViewModel

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
        title = { Text(text = "GAMES", fontSize = 18.sp) },
        backgroundColor = Color.Gray,
        contentColor = Color.Black
    )
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Profil
    )
    BottomNavigation(
        backgroundColor = Color.Gray,
        contentColor = Color.Black
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach{items ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = items.Icon), contentDescription = items.Title) },
                label = { Text(text = items.Title) },
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
fun ProfilScreen() {
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        Background(modifier = Modifier)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            BloomText(modifier = Modifier)
        }
    }
}
@Composable
fun Background(modifier: Modifier) {
    Image(
        modifier = modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.hosgeldiniz),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
    )
}
@Composable
fun BloomText(modifier: Modifier) {
    Text(
        modifier = modifier.paddingFromBaseline(top = 32.dp, bottom = 20.dp),
        text = "THANK YOU FOR YOUR INTEREST!",
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.subtitle1
    )
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