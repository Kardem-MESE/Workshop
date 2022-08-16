package com.example.games

sealed class NavigationItems(var route:String, var Icon:Int, var Title:String)
{
    object Home : NavigationItems("home",R.drawable.ic_home,"Home")
    object Settings : NavigationItems("settings",R.drawable.ic_settings,"Settings")
}
