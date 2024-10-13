package com.example.notesapp.data_layer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    fun upsert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM note order by dateDated asc")
    fun getAllNoteOrderedByDate(): Flow<List<Note>>

    @Query("select * from note order by title asc")
    fun getALlNoteOrderedByTitle(): Flow<List<Note>>
}