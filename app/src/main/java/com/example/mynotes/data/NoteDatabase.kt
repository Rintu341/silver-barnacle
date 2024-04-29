package com.example.mynotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynotes.model.Note
import com.example.mynotes.utils.DateConverter
import com.example.mynotes.utils.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class) // it tells database hoe to convert ony type to another
abstract class NoteDatabase : RoomDatabase() {
    abstract  fun noteDao() : NoteDatabaseDao

}