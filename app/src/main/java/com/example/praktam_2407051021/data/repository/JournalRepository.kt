package com.example.praktam_2407051021.data.repository

import com.example.praktam_2407051021.data.api.RetrofitClient
import com.example.praktam_2407051021.data.model.JournalNote

class JournalRepository {
    suspend fun getJournals(): List<JournalNote> {
        return try {
            RetrofitClient.instance.getJournals()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
