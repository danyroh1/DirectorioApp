package com.example.directorioapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.directorioapp.components.EmailTextField
import com.example.directorioapp.components.MainIconButton
import com.example.directorioapp.components.MainTextField
import com.example.directorioapp.components.MainTitle
import com.example.directorioapp.components.NameTextField
import com.example.directorioapp.components.PhoneTextField
import com.example.directorioapp.model.Contacto
import com.example.directorioapp.viewModels.ContactosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, contactosVM: ContactosViewModel) {
    // Definición de colores basados en el ContactCard
    val colorFondo = Color(0xFF10403B)  // Fondo claro
    val colorAppBar = Color(0xFF127369)  // Gris medio para la barra
    val colorBoton = Color(0xFF4C5958)   // Café oscuro para botones (contraste)
    val colorTexto = Color(0xFF212121)   // Texto oscuro
    val colorIconos = Color(0xFF10403B)  // Café oscuro para iconos

    Scaffold(
        containerColor = colorFondo,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Agregar Contacto",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorAppBar,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ContentAddView(paddingValues, navController, contactosVM, colorBoton, colorTexto)
    }
}

@Composable
fun ContentAddView(
    paddingValues: PaddingValues,
    navController: NavController,
    contactosVM: ContactosViewModel,
    buttonColor: Color,
    textColor: Color
) {
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        NameTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = "Nombre"
        )

        NameTextField(
            value = apellidoPaterno,
            onValueChange = { apellidoPaterno = it },
            label = "Apellido Paterno"
        )

        NameTextField(
            value = apellidoMaterno,
            onValueChange = { apellidoMaterno = it },
            label = "Apellido Materno"
        )

        PhoneTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = "Teléfono"
        )

        EmailTextField(
            value = correo,
            onValueChange = { correo = it },
            label = "Correo Electrónico"
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val nuevoContacto = Contacto(
                    nombre = nombre,
                    apellidoPaterno = apellidoPaterno,
                    apellidoMaterno = apellidoMaterno,
                    telefono = telefono,
                    correo = correo
                )
                contactosVM.addContacto(nuevoContacto)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Guardar Contacto",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}