package com.example.notesapp.ui_layer

import com.example.notesapp.data_layer.Note

sealed interface NoteEvent {

    object SortNotes: NoteEvent

    data class deleteNote(val note: Note): NoteEvent

    data class saveNote(val title: String, val description: String): NoteEvent

    data class titleChange(val title: String) : NoteEvent

    data class descriptionChange(val description: String) : NoteEvent

}