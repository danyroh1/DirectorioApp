package com.example.directorioapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.directorioapp.R
import com.example.directorioapp.model.Contacto

@Composable
fun MainTitle(title: String) {
    Text(
        text = title,
        color = Color(0xFFFFFFFF), // Blanco
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    iconResId: Int? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            leadingIcon = {
                iconResId?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = label,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) Color.Red else Color(0xFF127369),
                unfocusedBorderColor = if (isError) Color.Red else Color(0xFF4C5958),
                errorBorderColor = Color.Red
            ),
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 15.dp)
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Nombre",
    isError: Boolean = false,
    errorMessage: String? = null
) {
    MainTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        iconResId = R.drawable.user,
        isError = isError,
        errorMessage = errorMessage
    )
}

@Composable
fun PhoneTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Teléfono",
    isError: Boolean = false,
    errorMessage: String? = null
) {
    MainTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                onValueChange(newValue)
            }
        },
        label = label,
        iconResId = R.drawable.llamar,
        keyboardType = KeyboardType.Phone,
        isError = isError,
        errorMessage = errorMessage
    )
}

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Correo Electrónico",
    isError: Boolean = false,
    errorMessage: String? = null
) {
    MainTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        iconResId = R.drawable.carta,
        keyboardType = KeyboardType.Email,
        isError = isError,
        errorMessage = errorMessage
    )
}

@Composable
fun ContactCard(
    contacto: Contacto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBFBFBF)
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Manejo seguro de nombres compuestos
            Text(
                text = listOfNotNull(
                    contacto.nombre,
                    contacto.apellidoPaterno,
                    contacto.apellidoMaterno
                ).joinToString(" "),
                color = Color(0xFF212121),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.llamar),
                    contentDescription = "Teléfono",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF10403B)
                )
                Text(
                    text = contacto.telefono,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color(0xFF212121)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            // Solo mostrar correo si no es null ni está vacío
            contacto.correo?.takeIf { it.isNotBlank() }?.let { email ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.carta),
                        contentDescription = "Email",
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF10403B)
                    )
                    Text(
                        text = email,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color(0xFF212121)
                    )
                }
            }
        }
    }
}

@Composable
fun BarraBuscar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            onSearch(it)
        },
        label = { Text("Buscar contactos") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

