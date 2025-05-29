package com.example.directorioapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.directorioapp.viewModels.ContactosViewModel
import com.example.directorioapp.views.AddView
import com.example.directorioapp.views.EditView
import com.example.directorioapp.views.HomeView


@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController)
        }
        composable("AddView"){
            AddView(navController)
        }
        composable("EditView"){
            EditView(navController)
        }
    }
}
