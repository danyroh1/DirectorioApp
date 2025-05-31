package com.example.directorioapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.directorioapp.dataStore.StoreBoarding
import com.example.directorioapp.onBoardViews.MainOnBoarding
import com.example.directorioapp.viewModels.ContactosViewModel
import com.example.directorioapp.views.AddView
import com.example.directorioapp.views.EditView
import com.example.directorioapp.views.HomeView
import com.example.directorioapp.views.SplashScreen

@Composable
fun NavManager(contactosVM: ContactosViewModel) {
    val context= LocalContext.current
    val dataStore= StoreBoarding(context)
    val store=dataStore.getStoreBoarding.collectAsState(initial = true)

    val navController= rememberNavController()
    NavHost(navController=navController,
        startDestination= if(store.value==true) "Home" else "Splash")
    {
        composable("Splash"){
            SplashScreen(navController,store.value)
        }

        composable("onBoarding"){
            MainOnBoarding(navController,dataStore)
        }

        composable("Home") {
            HomeView(
                navController = navController,
                contactosVM = contactosVM
            )
        }

        composable("AddView") {
            AddView(
                navController = navController,
                contactosVM = contactosVM
            )
        }

        composable(
            route = "EditView/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0L
            EditView(
                navController = navController,
                contactosVM = contactosVM,
                id = id
            )
        }
    }
}