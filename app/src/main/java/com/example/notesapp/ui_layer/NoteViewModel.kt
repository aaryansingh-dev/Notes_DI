package com.example.notesapp.ui_layer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data_layer.NoteDatabase
import com.example.notesapp.data_layer.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(noteDatabase: NoteDatabase) : ViewModel() {

    private val dao = noteDatabase.dao
    private val isSortedByDateAdded = MutableStateFlow(true)
    private val notes = isSortedByDateAdded.flatMapLatest { sortDateBool ->
        if (sortDateBool) {
            dao.getAllNoteOrderedByDate()
        } else {
            dao.getALlNoteOrderedByTitle()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(NoteState())
    val state = combine(_state, isSortedByDateAdded, notes){state, isSortedByDateAdded, notes->
        state.copy(
            notes = notes
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NoteEvent)
    {
        when(event){
            is NoteEvent.SortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
            is NoteEvent.deleteNote -> {
                viewModelScope.launch {
                    dao.delete(event.note)
                }

            }
            is NoteEvent.saveNote -> {
                val note = Note(title = state.value.title, desciption = state.value.description, dateDated = System.currentTimeMillis())
                viewModelScope.launch {
                    dao.upsert(note)
                }

                _state.update {
                    it.copy(
                        title = "",
                        description = ""
                    )
                }
            }

            is NoteEvent.titleChange -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }

            is NoteEvent.descriptionChange -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }
        }
    }

}