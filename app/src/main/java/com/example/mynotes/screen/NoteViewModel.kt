package com.example.mynotes.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.NoteDataSource
import com.example.mynotes.model.Note
import com.example.mynotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

//    private var noteList = mutableStateListOf<Note>()
        private val _noteList = MutableStateFlow<List<Note>>(emptyList())
        val noteList = _noteList.asStateFlow()
    init {
        // At the time App start it load all the pre loaded notes
        //        noteList.addAll(NoteDataSource().loadNotes())
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged()
                .collect{listOfNotes->
                    if(listOfNotes.isNullOrEmpty())
                    {
                        Log.d("Empty","list empty")
                    }else
                    {
                        _noteList.value = listOfNotes
                    }

                }
        }
    }
     fun addNote(note :Note) = viewModelScope.launch { repository.addNote(note) }



     fun removeNote(note:Note) = viewModelScope.launch { repository.deleteNote(note) }


    //    suspend fun getAllNotes():List<Note>
    fun updateNote(note:Note) = viewModelScope.launch { repository.updateNote(note) }




}