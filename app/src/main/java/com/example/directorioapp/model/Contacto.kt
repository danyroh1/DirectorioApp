package com.example.directorioapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class Contacto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "apellido_paterno")
    val apellidoPaterno: String? = null, // Opcional

    @ColumnInfo(name = "apellido_materno")
    val apellidoMaterno: String? = null, // Opcional

    @ColumnInfo(name = "telefono")
    val telefono: String,

    @ColumnInfo(name = "correo")
    val correo: String? = null // Opcional
)