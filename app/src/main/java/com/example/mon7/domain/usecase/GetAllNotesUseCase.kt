package com.example.mon7.domain.usecase

import com.example.mon7.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor (
    private val noteRepository: NoteRepository
){
    fun getAllNotes() = noteRepository.getAllNotes()
}