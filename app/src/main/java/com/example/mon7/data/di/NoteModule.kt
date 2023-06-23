package com.example.mon7.data.di

import android.content.Context
import androidx.room.Room
import com.example.mon7.data.local.NoteDao
import com.example.mon7.data.local.NoteDatabase
import com.example.mon7.data.repository.NoteRepositoryImpl
import com.example.mon7.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NoteModule {
    @Provides
    @Singleton

    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase = Room.databaseBuilder(
        context, NoteDatabase::class.java, "note_db"
    ).build()


    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)

}