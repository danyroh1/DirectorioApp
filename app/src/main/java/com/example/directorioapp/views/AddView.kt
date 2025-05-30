package com.example.directorioapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.directorioapp.components.MainIconButton
import com.example.directorioapp.components.MainTextField
import com.example.directorioapp.components.MainTitle
import com.example.directorioapp.model.Contacto
import com.example.directorioapp.viewModels.ContactosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, contactosVM: ContactosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Agregar Contacto") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentAddContacto(it, navController, contactosVM)
    }
}

@Composable
fun ContentAddContacto(it: PaddingValues, navController: NavController, contactosVM: ContactosViewModel) {
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 70.dp)
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainTextField(value = nombre, onValueChange = { nombre = it }, label = "Nombre")
        MainTextField(value = apellidoPaterno, onValueChange = { apellidoPaterno = it }, label = "Apellido Paterno")
        MainTextField(value = apellidoMaterno, onValueChange = { apellidoMaterno = it }, label = "Apellido Materno")
        MainTextField(value = telefono, onValueChange = { telefono = it }, label = "Teléfono")
        MainTextField(value = correo, onValueChange = { correo = it }, label = "Correo")

        Button(onClick = {
            val nuevo = Contacto(
                nombre = nombre,
                apellidoPaterno = apellidoPaterno,
                apellidoMaterno = apellidoMaterno,
                telefono = telefono,
                correo = correo
            )
            contactosVM.addContacto(nuevo)
            navController.popBackStack()
        }) {
            Text("Guardar")
        }
    }
}
