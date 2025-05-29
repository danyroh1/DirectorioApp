package com.example.directorioapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.directorioapp.model.Contacto

@Database(entities = [Contacto::class], version = 1, exportSchema = false)
abstract class ContactosDatabase : RoomDatabase() {
    abstract fun contactosDao(): DbContactosDao
}
