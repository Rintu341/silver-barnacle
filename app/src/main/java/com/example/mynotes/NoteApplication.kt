package com.example.mynotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // It give the access to the whole application
class NoteApplication : Application()
{

}