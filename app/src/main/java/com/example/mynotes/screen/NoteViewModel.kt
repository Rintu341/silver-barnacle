package com.example.mynotes.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mynotes.data.NoteDataSource
import com.example.mynotes.model.Note

class NoteViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    init {
        // At the time App start it load all the pre loaded notes
        noteList.addAll(NoteDataSource().loadNotes())
    }
    fun addNote(note :Note)
    {
        noteList.add(note)
    }

    fun removeNote(note:Note)
    {
        noteList.remove(note)
    }

    fun getAllNotes():List<Note>
    {
        return noteList
    }
}