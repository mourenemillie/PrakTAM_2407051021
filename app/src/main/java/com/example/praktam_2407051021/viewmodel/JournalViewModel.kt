package com.example.praktam_2407051021.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2407051021.data.model.JournalNote
import com.example.praktam_2407051021.data.repository.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JournalViewModel : ViewModel() {

    private val _journalNotes = MutableStateFlow<List<JournalNote>>(emptyList())
    val journalNotes: StateFlow<List<JournalNote>> = _journalNotes.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    private val repository = JournalRepository()

    init {
        fetchJournalsFromApi()
    }

    private fun fetchJournalsFromApi() {
        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false
            
            val journalsFromApi = repository.getJournals()
            
            if (journalsFromApi.isNotEmpty()) {
                _journalNotes.value = journalsFromApi
                _isError.value = false
            } else {
                _isError.value = true 
            }
            
            _isLoading.value = false
        }
    }

    fun getNoteById(id: String): JournalNote? {
        return _journalNotes.value.find { it.id == id }
    }

    fun addNote(note: JournalNote) {
        val currentList = _journalNotes.value.toMutableList()
        currentList.add(0, note)
        _journalNotes.value = currentList
    }

    fun updateNote(updatedNote: JournalNote) {
        val currentList = _journalNotes.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            currentList[index] = updatedNote
            _journalNotes.value = currentList
        }
    }

    fun deleteNote(id: String) {
        val currentList = _journalNotes.value.toMutableList()
        currentList.removeAll { it.id == id }
        _journalNotes.value = currentList
    }
}