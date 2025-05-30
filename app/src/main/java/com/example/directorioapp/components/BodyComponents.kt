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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    iconVector: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = {
            Box(modifier = Modifier.size(24.dp)) {
                when {
                    iconResId != null -> Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = label,
                        modifier = Modifier.size(20.dp)
                    )
                    iconVector != null -> Icon(
                        imageVector = iconVector,
                        contentDescription = label,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(bottom = 15.dp)
    )
}

// Componente específico para teléfono con tu icono personalizado
@Composable
fun PhoneTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Teléfono"
) {
    MainTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        iconResId = R.drawable.llamar,
        keyboardType = KeyboardType.Phone
    )
}

// Componente específico para email con tu icono personalizado
@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Email"
) {
    MainTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        iconResId = R.drawable.carta,
        keyboardType = KeyboardType.Email
    )
}

// Componente específico para nombre con tu icono personalizado
@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Nombre"
) {
    MainTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        iconResId = R.drawable.user
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
            containerColor = Color(0xFFBFBFBF) // Fondo tarjeta claro
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${contacto.nombre} ${contacto.apellidoPaterno} ${contacto.apellidoMaterno}",
                color = Color(0xFF212121),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.llamar),
                    contentDescription = "Teléfono",
                    modifier = Modifier.size(24.dp), // Aumenté el tamaño para mejor visibilidad
                    tint = Color(0xFF10403B) // Color café oscuro consistente
                )
                Text(
                    text = contacto.telefono,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color(0xFF212121) // Texto oscuro
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.carta),
                    contentDescription = "Email",
                    modifier = Modifier.size(24.dp), // Mismo tamaño que el de teléfono
                    tint = Color(0xFF10403B) // Mismo color que el de teléfono
                )
                Text(
                    text = contacto.correo.toString(),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color(0xFF212121) // Texto oscuro
                )
            }
        }
    }
}