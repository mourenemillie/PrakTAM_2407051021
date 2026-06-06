package com.example.praktam_2407051021.viewmodel

import androidx.lifecycle.ViewModel
import com.example.praktam_2407051021.model.JournalNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class JournalViewModel : ViewModel() {

    private val _journalNotes = MutableStateFlow<List<JournalNote>>(emptyList())
    val journalNotes: StateFlow<List<JournalNote>> = _journalNotes.asStateFlow()

    init {
        // Init with dummy
        _journalNotes.value = listOf(
            JournalNote(
                id = "1",
                date = "Mon, 24 Feb",
                title = "Morning Clarity",
                tag = "Reflection",
                mantra = "\"I deserve so much more in return.\"",
                gratefulFor = "Olahraga pagi bareng teman, berhasil bikin desain Jetpack Compose yang rapi",
                thingsIDid = listOf("Nugas", "Clean up my room", "Latihan pensi"),
                peopleIMet = listOf("Mama", "Anak sekret", "Teman pensi"),
                whatILearnt = listOf("Compose Layouts", "State Management"),
                thingsToTomorrow = "Fokus kuliah pagi, kerjain sisa tugas laporan.",
                wantToRemember = "Take konten sama maba buat ospek",
                imageUrl = "https://i.pinimg.com/736x/88/d4/4d/88d44db2d895dc5727c0156f6c66da23.jpg",
                musicTrack = "Futile Devices\nSufjan Stevens"
            ),
            JournalNote(
                id = "2",
                date = "Tue, 25 Feb",
                title = "Ocean Walk",
                tag = "Relaxing",
                mantra = "\"Keep moving forward.\"",
                gratefulFor = "Cuaca cerah hari ini.",
                thingsIDid = listOf("Jalan-jalan", "Makan enak"),
                peopleIMet = listOf("Keluarga"),
                whatILearnt = listOf("Resep baru"),
                thingsToTomorrow = "Istirahat",
                wantToRemember = "Beli tiket konser",
                imageUrl = "https://i.pinimg.com/736x/f6/1e/d7/f61ed7dbb359cc021ad71370fa3f8da2.jpg",
                musicTrack = "Would That I\nHozier"
            )
        )
    }

    fun getNoteById(id: String): JournalNote? {
        return _journalNotes.value.find { it.id == id }
    }

    fun addNote(note: JournalNote) {
        val currentList = _journalNotes.value.toMutableList()
        currentList.add(0, note) // Add to top
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
