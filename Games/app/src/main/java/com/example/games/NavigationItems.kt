package com.example.games

sealed class NavigationItems(var route:String, var Icon:Int, var Title:String)
{
    object Home : NavigationItems("home",R.drawable.ic_home,"Home")
    object Profil : NavigationItems("profil",R.drawable.ic_profile,"Profil")
}
