package com.example.mynotes.utils

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(time:Long):String
{
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa"
    , java.util.Locale.getDefault())
    return format.format(date)
}