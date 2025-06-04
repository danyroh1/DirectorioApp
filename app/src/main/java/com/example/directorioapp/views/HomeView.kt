package com.example.directorioapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.directorioapp.components.*
import com.example.directorioapp.model.Contacto
import com.example.directorioapp.viewModels.ContactosViewModel
import kotlinx.coroutines.delay
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, contactosVM: ContactosViewModel) {
    // 1. Verificar si viene del OnBoarding (opcional: mostrar mensaje de bienvenida)
    val showWelcome = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Mostrar mensaje por 2 segundos
        showWelcome.value = false
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Directorio") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF127369) // Manteniendo tu color
                )
            )
        },
        floatingActionButton = {
            FloatButton(
                buttonColor = Color(0xFF4C5958)
            ) {
                navController.navigate("AddView")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // 2. Mensaje temporal de bienvenida (opcional)
            if (showWelcome.value) {
                WelcomeMessage()
            }

            // 3. Tu contenido original del directorio
            ContentHomeView(navController, contactosVM)
        }
    }
}

@Composable
private fun WelcomeMessage() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4C5958))
    ) {
        Text(
            text = "¡Bienvenido a tu Directorio!",
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun ContentHomeView(
    navController: NavController,
    contactosVM: ContactosViewModel
) {
    val contactosList by contactosVM.contactosList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    val contactoAEliminar = remember { mutableStateOf<Contacto?>(null) }

    // Filtrar y luego ordenar la lista
    val contactosFiltradosYOrdenados = contactosList
        .filter {
            it.nombre.contains(searchQuery, ignoreCase = true) ||
                    (it.apellidoPaterno?.contains(searchQuery, ignoreCase = true) ?: false) ||
                    (it.apellidoMaterno?.contains(searchQuery, ignoreCase = true) ?: false) ||
                    it.telefono.contains(searchQuery)
        }
        .sortedBy { it.nombre.lowercase() }

    Column {
        BarraBuscar(onSearch = { searchQuery = it })

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                confirmButton = {
                    TextButton(onClick = {
                        contactoAEliminar.value?.let { contactosVM.deleteContacto(it) }
                        openDialog.value = false
                    }) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openDialog.value = false }) {
                        Text("Cancelar")
                    }
                },
                title = { Text("Confirmar eliminación") },
                text = { Text("¿Estás seguro de que deseas eliminar este contacto?") }
            )
        }

        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            items(contactosFiltradosYOrdenados) { contacto ->
                val deleteAction = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = {
                        contactoAEliminar.value = contacto
                        openDialog.value = true
                    }
                )

                SwipeableActionsBox(
                    endActions = listOf(deleteAction),
                    swipeThreshold = 150.dp
                ) {
                    ContactCard(
                        contacto = contacto,
                        onClick = { navController.navigate("EditView/${contacto.id}") }
                    )
                }
            }
        }
    }
}
