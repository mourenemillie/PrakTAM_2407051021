package com.example.praktam_2407051021.data.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class JournalNote(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("date") val date: String,
    @SerializedName("title") val title: String,
    @SerializedName("tag") val tag: String,
    @SerializedName("mantra") val mantra: String,
    @SerializedName("gratefulFor") val gratefulFor: String,
    @SerializedName("thingsIDid") val thingsIDid: List<String>,
    @SerializedName("peopleIMet") val peopleIMet: List<String>,
    @SerializedName("whatILearnt") val whatILearnt: List<String>,
    @SerializedName("thingsToTomorrow") val thingsToTomorrow: String,
    @SerializedName("wantToRemember") val wantToRemember: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("musicTrack") val musicTrack: String
)
