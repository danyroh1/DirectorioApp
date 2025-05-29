package com.example.directorioapp.di

import android.content.Context
import androidx.room.Room
import com.example.directorioapp.room.ContactosDatabase
import com.example.directorioapp.room.DbContactosDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideContactosDao(contactosDatabase: ContactosDatabase): DbContactosDao {
        return contactosDatabase.contactosDao()
    }

    @Singleton
    @Provides
    fun provideContactosDatabase(@ApplicationContext context: Context): ContactosDatabase {
        return Room.databaseBuilder(
            context,
            ContactosDatabase::class.java,
            "contactos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}
