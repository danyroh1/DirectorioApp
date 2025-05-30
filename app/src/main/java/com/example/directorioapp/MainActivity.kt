package com.example.directorioapp;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.directorioapp.navigation.NavManager
import com.example.directorioapp.ui.theme.DirectorioAppTheme
import com.example.directorioapp.viewModels.ContactosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuración de Edge-to-EdgeenableEdgeToEdge()
        val contactosVM: ContactosViewModel by viewModels()
        setContent {
            DirectorioAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(contactosVM)
                }
            }
        }
    }
}
