package com.example.praktam_2407051021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam_2407051021.model.JournalSection
import com.example.praktam_2407051021.model.JournallingSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                DaftarJournalScreen()
            }
        }
    }
}

@Composable
fun DaftarJournalScreen(modifier: Modifier = Modifier) {
    val dummyJournal = JournallingSource.dummyJournal
    val beigeBackground = Color(0xFFEBE3D5)
    
    // implementasi LazyColumn per modul 6
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(beigeBackground)
            .statusBarsPadding(),
        contentPadding = PaddingValues(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // scope item buat naruh header dan LazyRow
        item {
            Text(
                text = "Bloom.ly \uD83C\uDF38",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF8A817C),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            
            Text(
                text = "Daily Check-in",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                color = Color(0xFF3E3E3E),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            Text(
                text = "Recent Highlights",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E3E3E),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // LazyRow untuk menampilkan row item secara horizontal
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dummyJournal) { section ->
                    HighlightRowItem(section = section)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Today's Log",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E3E3E),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        // scope function items buat nampilin daftar section entry jurnal utama
        items(dummyJournal) { section ->
            DetailScreen(section = section)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* TODO: fitur simpan */ },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8A817C))
            ) {
                Text(text = "Save Entry", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun HighlightRowItem(section: JournalSection) {
    // Card dipakai untuk list item horizontal (komponen wajib modul 6)
    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = section.gambar),
                contentDescription = section.sectionName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = section.sectionName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5A5A5A)
                )
            }
        }
    }
}

@Composable
fun DetailScreen(section: JournalSection) {
    // nyimpen state tombol love
    var isFavorite by remember { mutableStateOf(false) }

    // Memasukkan seluruh bagian kolom ke dalam Card sesuai modul 6
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = section.sectionName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF5A5A5A)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                // pake Box biar icon love bisa numpuk di atas gambar
                Box {
                    // gambar thumbnail
                    Image(
                        painter = painterResource(id = section.gambar),
                        contentDescription = section.sectionName,
                        modifier = Modifier
                            .size(80.dp) // size dipasin biar button muat
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    
                    // button love ditaruh di pojok kanan atas
                    IconButton(
                        onClick = { isFavorite = !isFavorite },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(32.dp)
                            .padding(2.dp)
                    ) {
                        // ganti icon & warna kl diklik
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                            tint = if (isFavorite) Color.Red else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    text = section.content,
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DaftarJournalPreview() {
    MaterialTheme {
        DaftarJournalScreen()
    }
}