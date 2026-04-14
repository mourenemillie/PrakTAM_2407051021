package com.example.praktam_2407051021.model

import com.example.praktam_2407051021.R

object JournallingSource {
    val dummyJournal = listOf(
        JournalSection(
            sectionName = "📅 Hari/Tanggal",
            content = "Monday, 24 Februari 2025",
            gambar = R.drawable.header
        ),
        JournalSection(
            sectionName = "💫 Daily Mantra",
            content = "I deserve so much more in return.",
            gambar = R.drawable.mood
        ),
        JournalSection(
            sectionName = "🌟 What the good things about today",
            content = "Olahraga pagi bareng teman, berhasil bikin desain Jetpack Compose yang rapi, masak makanan kesukaan sendiri, dan asyik DIY kaktus.",
            gambar = R.drawable.pfp
        ),
        JournalSection(
            sectionName = "👥 People I met today",
            content = "Mama, anak-anak sekret, dan kawan-kawan dari pensi kampus.",
            gambar = R.drawable.profile
        ),
        JournalSection(
            sectionName = "✅ Plan for tomorrow",
            content = "Fokus kuliah pagi, kerjain sisa tugas laporan, ngampus lagi siang hari, dan sorenya santai nongkrong di cafe favorit sambil nonton idol.",
            gambar = R.drawable.idol
        )
    )
}