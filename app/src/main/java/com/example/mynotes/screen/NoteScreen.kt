package com.example.mynotes.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynotes.R
import com.example.mynotes.component.NoteButton
import com.example.mynotes.component.NoteInputText
import com.example.mynotes.data.NoteDataSource
import com.example.mynotes.model.Note
import com.example.mynotes.utils.formatDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes : List<Note>,
    onAddNote:(Note) -> Unit,
    onRemoveNote:(Note) ->Unit
)
{
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(title = { Text(stringResource(id = R.string.app_name))},
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = null)        
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(Color.Gray)
            )

        //Content
        Column (
            modifier = Modifier.padding(top = 4.dp),
            verticalArrangement =  Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = {
                if(it.all { char ->
                    char.isLetter() || char.isWhitespace()
                    }) title = it
            } )
            NoteInputText(
                text = description,
                label = "Description",
                onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                } )
            NoteButton(
                text = "Save",
                onClick = {
                            //Save title and description
                              if(title.isNotEmpty() && description.isNotEmpty())
                              {
                                  onAddNote(Note(title = title, description = description))
                                  title = ""
                                  description = ""
                              }
                          },
                enabled = true,
                colors = ButtonDefaults.buttonColors(Color.Green)
            )

        }
        Divider(modifier = Modifier.padding(4.dp))
        LazyColumn {
            items(notes)
            {note->
                NoteRow(note = note) {
                    onRemoveNote(it)
                }
            }
        }
    }
}


@Composable
fun NoteRow(
    note: Note,
    modifier:Modifier = Modifier,
    onNoteClicked:(Note) -> Unit
){
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFF83AFD6),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                note.title,
                style = MaterialTheme.typography.titleLarge
                )
            Text(note.description,style = MaterialTheme.typography.titleMedium)
            Text(formatDate(note.entryDate.time),
                style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview()
{
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}