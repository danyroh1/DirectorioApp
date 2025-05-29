package com.example.directorioapp.repository

import com.example.directorioapp.model.Contacto
import com.example.directorioapp.room.DbContactosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactosRepository @Inject constructor(private val contactosDao: DbContactosDao) {

    suspend fun addContacto(contacto: Contacto) = contactosDao.insert(contacto)

    suspend fun updateContacto(contacto: Contacto) = contactosDao.update(contacto)

    suspend fun deleteContacto(contacto: Contacto) = contactosDao.delete(contacto)

    fun getAllContactos(): Flow<List<Contacto>> = contactosDao.getContactos().flowOn(Dispatchers.IO).conflate()

    fun getContactoById(id: Long): Flow<Contacto> = contactosDao.getContactoById(id).flowOn(Dispatchers.IO).conflate()
}
