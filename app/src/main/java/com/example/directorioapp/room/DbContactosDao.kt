package com.example.directorioapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.directorioapp.model.Contacto
import kotlinx.coroutines.flow.Flow

@Dao
interface DbContactosDao {

    @Query("SELECT * FROM contactos")
    fun getContactos(): Flow<List<Contacto>>

    @Query("SELECT * FROM contactos WHERE id = :id")
    fun getContactoById(id: Long): Flow<Contacto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contacto: Contacto)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contacto: Contacto)

    @Delete
    suspend fun delete(contacto: Contacto)
}