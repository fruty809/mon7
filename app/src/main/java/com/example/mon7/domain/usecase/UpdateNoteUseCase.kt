package com.example.mon7.domain.usecase

import com.example.mon7.domain.model.Note
import com.example.mon7.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun updateNote(note: Note) = noteRepository.updateNote(note)
}