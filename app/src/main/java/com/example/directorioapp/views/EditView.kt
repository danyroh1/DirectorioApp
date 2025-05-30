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
fun EditView(navController: NavController, contactosVM: ContactosViewModel, id: Long) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Editar Contacto") },
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
        ContentEditContacto(it, navController, contactosVM, id)
    }
}

@Composable
fun ContentEditContacto(
    it: PaddingValues,
    navController: NavController,
    contactosVM: ContactosViewModel,
    id: Long
) {
    val contacto = contactosVM.contactosList.collectAsState().value.find { it.id == id }

    var nombre by remember { mutableStateOf(contacto?.nombre ?: "") }
    var apellidoPaterno by remember { mutableStateOf(contacto?.apellidoPaterno ?: "") }
    var apellidoMaterno by remember { mutableStateOf(contacto?.apellidoMaterno ?: "") }
    var telefono by remember { mutableStateOf(contacto?.telefono ?: "") }
    var correo by remember { mutableStateOf(contacto?.correo ?: "") }

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
            val contactoActualizado = Contacto(
                id = id,
                nombre = nombre,
                apellidoPaterno = apellidoPaterno,
                apellidoMaterno = apellidoMaterno,
                telefono = telefono,
                correo = correo
            )
            contactosVM.updateContacto(contactoActualizado)
            navController.popBackStack()
        }) {
            Text("Guardar Cambios")
        }
    }
}
