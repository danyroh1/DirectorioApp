package com.example.directorioapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.directorioapp.model.Contacto
import com.example.directorioapp.repository.ContactosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ContactosViewModel @Inject constructor(private val repository: ContactosRepository) : ViewModel() {

    private val _contactosList = MutableStateFlow<List<Contacto>>(emptyList())
    val contactosList = _contactosList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllContactos().collect { items ->
                if(items.isEmpty()){
                    _contactosList.value = emptyList()
                }else{
                    _contactosList.value = items
                }
            }
        }
    }

    fun addContacto(contacto: Contacto) = viewModelScope.launch { repository.addContacto(contacto) }
    fun updateContacto(contacto: Contacto) = viewModelScope.launch { repository.updateContacto(contacto) }
    fun deleteContacto(contacto: Contacto) = viewModelScope.launch { repository.deleteContacto(contacto) }


}
