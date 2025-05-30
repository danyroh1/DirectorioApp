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
fun NavManager(contactosVM: ContactosViewModel ){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController,contactosVM)
        }
        composable("AddView"){
            AddView(navController,contactosVM)
        }
        composable("EditView/{id}",arguments = listOf(navArgument("id"){
            type= NavType.LongType})){
            val id=it.arguments?.getLong("id")?:0
            EditView(navController,contactosVM,id)
        }
    }
}
