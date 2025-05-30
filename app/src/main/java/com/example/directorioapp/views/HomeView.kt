package com.example.directorioapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.directorioapp.components.ContactCard
import com.example.directorioapp.components.FloatButton
import com.example.directorioapp.components.MainTitle
import com.example.directorioapp.viewModels.ContactosViewModel

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
        ContentHomeView(it, navController, contactosVM)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, contactosVM: ContactosViewModel) {
    Column(modifier = Modifier.padding(it)) {
        val contactosList by contactosVM.contactosList.collectAsState()

        LazyColumn {
            items(contactosList) { contacto ->
                ContactCard(contacto = contacto)
            }
        }
    }
}
