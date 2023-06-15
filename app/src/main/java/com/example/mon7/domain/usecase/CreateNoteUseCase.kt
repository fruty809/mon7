package com.example.mon7.domain.usecase

import com.example.mon7.domain.model.Note
import com.example.mon7.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun getCreateNote(note:Note) = noteRepository.createNote(note)
}