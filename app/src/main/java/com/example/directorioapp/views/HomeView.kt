// HomeView.kt
package com.example.directorioapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.directorioapp.components.ContactCard
import com.example.directorioapp.components.FloatButton
import com.example.directorioapp.components.MainTitle
import com.example.directorioapp.viewModels.ContactosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, contactosVM: ContactosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Directorio") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, contactosVM) // Pasamos navController aquí
    }
}

@Composable
fun ContentHomeView(
    it: PaddingValues,
    navController: NavController, // Añadimos navController como parámetro
    contactosVM: ContactosViewModel
) {
    Column(modifier = Modifier.padding(it)) {
        val contactosList by contactosVM.contactosList.collectAsState()

        LazyColumn {
            items(contactosList) { contacto ->
                val deleteAction = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = {
                        contactosVM.deleteContacto(contacto)
                    }
                )

                SwipeableActionsBox(
                    endActions = listOf(deleteAction),
                    swipeThreshold = 150.dp
                ) {
                    // Hacemos que la ContactCard sea clickeable
                    ContactCard(
                        contacto = contacto,
                        onClick = {
                            navController.navigate("EditView/${contacto.id}")
                        }
                    )
                }
            }
        }
    }
}