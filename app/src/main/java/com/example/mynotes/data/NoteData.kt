package com.example.mynotes.data

import com.example.mynotes.model.Note

class NoteDataSource{
    fun loadNotes():List<Note>
    {
        return listOf(
            Note(
                title = "My Life",
                description = "I will have a great life"
            ),
            Note(
                title = "My Goal",
                description = "Become an Software engineer"
            )

        )
    }
}