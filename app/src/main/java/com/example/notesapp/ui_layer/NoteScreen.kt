package com.example.notesapp.ui_layer

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Notes App", modifier = Modifier.weight(1f), fontSize = 17.sp)
            IconButton(onClick = {
                onEvent(NoteEvent.SortNotes)
            }) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.Sort, contentDescription = "To sort the notes")
            }
        }
    },
        floatingActionButton = {
            FloatingActionButton(onClick ={
                navController.navigate("add_screen")
            } )
            {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Takes to add screen")
            }
        }) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = it) {

            items(state.notes)
            {
                Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(
                    Color.Gray).padding(12.dp)) {
                    Column {
                        Text(text = it.title)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = it.desciption)
                    }
                    Spacer(modifier = Modifier.width(60.dp))
                    IconButton(onClick = {
                        onEvent(NoteEvent.deleteNote(it))
                    })
                    {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "To delete a specific note")
                    }
                }
            }
        }
    }

}