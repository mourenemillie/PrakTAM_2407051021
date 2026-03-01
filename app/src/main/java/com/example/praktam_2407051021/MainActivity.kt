package com.example.praktam_2407051021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam_2407051021.model.JournallingSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Greeting(
                    name = "Nana",
                    npm = "2407051021",
                    modifier = Modifier.padding(paddingValues = innerPadding)
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, npm: String, modifier: Modifier = Modifier) {
    val journal = JournallingSource.dummyJournal[0]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Halo, saya $name dengan NPM $npm siap belajar Compose! Ide project saya adalah aplikasi Bloom.ly - Daily Journaling.",
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = journal.gambar),
            contentDescription = "Foto Jurnal",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "📅 ${journal.tanggal}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = "💫 Daily Mantra: ${journal.mantra}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "🌟 People I Met: ${journal.people}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "💭 Today I felt: ${journal.perasaan}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "✅ Things I Did: ${journal.kegiatan}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "🔖 What i want to remember: ${journal.kenangan}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting(name = "Nana", npm = "2407051021")
}