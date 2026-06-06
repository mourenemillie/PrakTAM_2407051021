package com.example.praktam_2407051021.data.api

import com.example.praktam_2407051021.data.model.JournalNote
import retrofit2.http.GET

interface ApiService {
    @GET("gistfile1.txt")
    suspend fun getJournals(): List<JournalNote>
}
