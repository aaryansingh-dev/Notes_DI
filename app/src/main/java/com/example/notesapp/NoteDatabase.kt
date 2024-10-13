package com.example.notesapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.data_layer.Note
import com.example.notesapp.data_layer.NoteDao

@Database(exportSchema = true, entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract val dao: NoteDao
}