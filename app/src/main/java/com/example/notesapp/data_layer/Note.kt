package com.example.notesapp.data_layer

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.processing.Generated


@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var desciption: String,
    var dateDated: Long
)