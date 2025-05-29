package com.example.directorioapp.state

data class ContactoState(
    val isFormValid: Boolean = false,
    val showSaveButton: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val title: String = "",
)
