package com.example.directorioapp.components

import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.directorioapp.R

@Composable
fun MainTitle(title: String) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = label)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(bottom = 15.dp)
    )
}

// Componente específico para campos de teléfono
@OptIn(ExperimentalMaterial3Api::class)
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
        icon = Icons.Default.Phone
    )
}

// Componente específico para campos de email
@OptIn(ExperimentalMaterial3Api::class)
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
        icon = Icons.Default.Email
    )
}

// Componente específico para campos de nombre
@OptIn(ExperimentalMaterial3Api::class)
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
        icon = Icons.Default.Person
    )
}

@Composable
fun ContactCard(
    nombreCompleto: String,
    telefono: String,
    email: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = nombreCompleto,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Teléfono",
                    tint = Color.Gray
                )
                Text(
                    text = telefono,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color.Gray
                )
                Text(
                    text = email,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}