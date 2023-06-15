package com.example.mon7.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mon7.domain.model.Note
import com.example.mon7.domain.usecase.CreateNoteUseCase
import com.example.mon7.domain.usecase.UpdateNoteUseCase
import com.example.mon7.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateNoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _updateNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val updateNoteState = _updateNoteState.asStateFlow()

    fun createNote(note: Note) {
        viewModelScope.launch {
            createNoteUseCase.getCreateNote(note).collect() { res ->
                when (res) {
                    is Resource.Error -> {
                        _createNoteState.value = UIState.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _createNoteState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        _createNoteState.value = UIState.Success(res.data!!)

                    }
                }

            }

        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase.updateNote(note).collect() { res ->
                when (res) {
                    is Resource.Error -> {
                        _updateNoteState.value = UIState.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _updateNoteState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        _updateNoteState.value = UIState.Success(res.data!!)

                    }
                }

            }

        }
    }
}