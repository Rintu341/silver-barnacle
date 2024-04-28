package com.example.mynotes.model

import java.time.LocalDateTime
import java.util.*

data class Note(
    val  id: UUID = UUID.randomUUID(),
    var title : String,
    var description:String,
    var entryDate : LocalDateTime = LocalDateTime.now()
)
