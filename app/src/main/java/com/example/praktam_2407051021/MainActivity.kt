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
        // Main title for the Daily Check-in
        Text(
            text = "Daily Check-in",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            color = Color(0xFF3E3E3E),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        )
        
        // Loop over the 5 sections
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
    // Making it look like the white blocks in the reference image
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
            // Incorporating the image as a small thumbnail to legally satisfy the module constraints without ruining the design
            Image(
                painter = painterResource(id = section.gambar),
                contentDescription = section.sectionName,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
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