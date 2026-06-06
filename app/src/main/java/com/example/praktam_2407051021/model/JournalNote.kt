package com.example.praktam_2407051021.model

import java.util.UUID

data class JournalNote(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val title: String, 
    val tag: String,   
    val mantra: String, 
    val gratefulFor: String,
    val thingsIDid: List<String>,
    val peopleIMet: List<String>,
    val whatILearnt: List<String>,
    val thingsToTomorrow: String,
    val wantToRemember: String,
    val imageUrl: String,
    val musicTrack: String
)
