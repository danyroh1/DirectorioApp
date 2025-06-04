package com.example.directorioapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.directorioapp.components.EmailTextField
import com.example.directorioapp.components.NameTextField
import com.example.directorioapp.components.PhoneTextField
import com.example.directorioapp.model.Contacto
import com.example.directorioapp.viewModels.ContactosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(navController: NavController, contactosVM: ContactosViewModel, id: Long) {
    // Colores consistentes con AddView y ContactCard
    val colorFondo = Color(0xFF10403B)  // Fondo claro
    val colorAppBar = Color(0xFF127369)  // Gris medio para la barra
    val colorBoton = Color(0xFF4C5958)   // Café oscuro para botones
    val colorTexto = Color(0xFF212121)   // Texto oscuro

    Scaffold(
        containerColor = colorFondo,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Editar Contacto",
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
        ContentEditContacto(
            paddingValues = paddingValues,
            navController = navController,
            contactosVM = contactosVM,
            id = id,
            buttonColor = colorBoton,
            textColor = colorTexto
        )
    }
}

@Composable
fun ContentEditContacto(
    paddingValues: PaddingValues,
    navController: NavController,
    contactosVM: ContactosViewModel,
    id: Long,
    buttonColor: Color,
    textColor: Color
) {
    val contacto = contactosVM.contactosList.collectAsState().value.find { it.id == id }

    // Estados para los campos - Usamos el operador elvis (?:) para convertir null a ""
    var nombre by remember { mutableStateOf(contacto?.nombre ?: "") }
    var apellidoPaterno by remember { mutableStateOf(contacto?.apellidoPaterno ?: "") }
    var apellidoMaterno by remember { mutableStateOf(contacto?.apellidoMaterno ?: "") }
    var telefono by remember { mutableStateOf(contacto?.telefono ?: "") }
    var correo by remember { mutableStateOf(contacto?.correo ?: "") }

    // Estados para errores
    var nombreError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }
    var correoError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

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
            onValueChange = {
                nombre = it
                nombreError = false
            },
            label = "Nombre",
            isError = nombreError,
            errorMessage = if (nombreError) errorMessage else null
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
            onValueChange = {
                telefono = it
                telefonoError = false
            },
            isError = telefonoError,
            errorMessage = if (telefonoError) errorMessage else null
        )

        EmailTextField(
            value = correo,
            onValueChange = {
                correo = it
                correoError = false
            },
            label = "Correo Electrónico",
            isError = correoError,
            errorMessage = if (correoError) errorMessage else null
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                // Validación de campos
                when {
                    nombre.isBlank() -> {
                        errorMessage = "El nombre es obligatorio"
                        nombreError = true
                    }
                    telefono.isBlank() -> {
                        errorMessage = "El teléfono es obligatorio"
                        telefonoError = true
                    }
                    !telefono.matches(Regex("^[0-9]{10}\$")) -> {
                        errorMessage = "El teléfono debe tener 10 dígitos"
                        telefonoError = true
                    }
                    correo.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> {
                        errorMessage = "Ingrese un correo electrónico válido"
                        correoError = true
                    }
                    else -> {
                        val contactoActualizado = Contacto(
                            id = id,
                            nombre = nombre.trim(),
                            apellidoPaterno = apellidoPaterno.trim().takeIf { it.isNotBlank() },
                            apellidoMaterno = apellidoMaterno.trim().takeIf { it.isNotBlank() },
                            telefono = telefono.trim(),
                            correo = correo.trim().takeIf { it.isNotBlank() }
                        )
                        contactosVM.updateContacto(contactoActualizado)
                        navController.popBackStack()
                    }
                }
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
                text = "Guardar Cambios",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}