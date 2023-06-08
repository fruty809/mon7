package com.example.mon7.data

import com.example.mon7.data.model.NoteEntity
import com.example.mon7.domain.model.Note

fun Note.toEntity() = NoteEntity(this.id,title,description)
fun NoteEntity.toNote() = Note(this.id,title,description)