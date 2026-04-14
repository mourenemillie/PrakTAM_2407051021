package com.example.praktam_2407051021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                DaftarJournalScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun DaftarJournalScreen(modifier: Modifier = Modifier) {
    val dummyJournal = JournallingSource.dummyJournal
    val beigeBackground = Color(0xFFEBE3D5)
    
    Column(
        modifier = modifier
            .background(beigeBackground)
            .verticalScroll(rememberScrollState())
            .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
    ) {

        Text(
            text = "Bloom.ly \uD83C\uDF38",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFF8A817C),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        
        // judul utama halaman
        Text(
            text = "Daily Check-in",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            color = Color(0xFF3E3E3E),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        )
        
        // looping data jurnal buat nampilin tiap section
        dummyJournal.forEach { section ->
            DetailScreen(section = section)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Aksi simpan jurnal */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8A817C))
        ) {
            Text(text = "Save Entry", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun DetailScreen(section: JournalSection) {
    // nyimpen state tombol love (LKP 4)
    var isFavorite by remember { mutableStateOf(false) }

    // kotak putih rounded buat tiap item
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
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

@Preview(showBackground = true)
@Composable
fun DaftarJournalPreview() {
    MaterialTheme {
        DaftarJournalScreen()
    }
}