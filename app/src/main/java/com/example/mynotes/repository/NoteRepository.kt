package com.example.mynotes.repository

import com.example.mynotes.data.NoteDatabaseDao
import com.example.mynotes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//It is an abstract layer above the NoteDatabaseDao
class NoteRepository @Inject constructor(private val noteDatabaseDao : NoteDatabaseDao) {
    suspend fun  addNote(note: Note) = noteDatabaseDao.insert(note)

    suspend fun  updateNote(note:Note) = noteDatabaseDao.update(note)

    suspend fun deleteNote(note:Note) = noteDatabaseDao.deleteNote(note)

    suspend fun deleteAll() = noteDatabaseDao.deleteAll()

    fun getAllNotes():Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}