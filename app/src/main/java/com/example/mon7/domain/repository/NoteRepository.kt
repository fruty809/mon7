package com.example.mon7.domain.repository

import com.example.mon7.domain.model.Note
import com.example.mon7.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    fun getAllNotes(): Flow<Resource<List<Note>>>

    fun createNote(note: Note): Flow<Resource<Unit>>

    fun updateNote(note: Note): Flow<Resource<Unit>>

    fun deleteNote(note: Note): Flow<Resource<Unit>>
}