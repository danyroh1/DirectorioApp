package com.example.directorioapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.directorioapp.state.ContactoState
import kotlinx.coroutines.Job

class DirectorioViewModel:ViewModel() {
    var state by mutableStateOf(ContactoState())
        private set
    var nombre by mutableStateOf<Job?>(null)
        private set
    var telefono by mutableStateOf<Job?>(null)
    fun   onValue(value: String){
        state = state.copy(
            title = value
        )
}
}