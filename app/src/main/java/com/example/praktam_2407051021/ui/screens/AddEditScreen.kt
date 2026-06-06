package com.example.praktam_2407051021.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.praktam_2407051021.model.JournalNote
import com.example.praktam_2407051021.viewmodel.JournalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: JournalViewModel,
    journalId: String?
) {
    val existingNote = if (journalId != null) viewModel.getNoteById(journalId) else null

    var searchMusic by remember { mutableStateOf("") }
    var gratefulFor by remember { mutableStateOf(existingNote?.gratefulFor ?: "") }
    var dailyMantra by remember { mutableStateOf(existingNote?.mantra ?: "") }
    var peopleIMet by remember { mutableStateOf(existingNote?.peopleIMet?.joinToString(", ") ?: "") }
    var thingsIDid by remember { mutableStateOf(existingNote?.thingsIDid?.joinToString(", ") ?: "") }
    var whatILearnt by remember { mutableStateOf(existingNote?.whatILearnt?.joinToString(", ") ?: "") }
    var thingsTomorrow by remember { mutableStateOf(existingNote?.thingsToTomorrow ?: "") }
    var wantToRemember by remember { mutableStateOf(existingNote?.wantToRemember ?: "") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp).statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground)
                }
            }
        },
        bottomBar = {
            Button(
                onClick = {
                    val newNote = JournalNote(
                        id = existingNote?.id ?: java.util.UUID.randomUUID().toString(),
                        date = "Mon, 24 Feb",
                        title = "New Check-in",
                        tag = "Reflection",
                        mantra = dailyMantra.ifEmpty { "My Mantra" },
                        gratefulFor = gratefulFor,
                        thingsIDid = thingsIDid.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                        peopleIMet = peopleIMet.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                        whatILearnt = whatILearnt.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                        thingsToTomorrow = thingsTomorrow,
                        wantToRemember = wantToRemember,
                        imageUrl = existingNote?.imageUrl ?: "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=400&q=80",
                        musicTrack = "New Track\nArtist"
                    )
                    if (existingNote != null) viewModel.updateNote(newNote) else viewModel.addNote(newNote)
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("SIMPAN ENTRI \u2714", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Bloom.ly \uD83C\uDF38",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
                    Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Monday, 24 February 2025", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                OutlinedTextField(
                    value = searchMusic,
                    onValueChange = { searchMusic = it },
                    placeholder = { Text("Cari lagu yang lagi didengerin...", fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Daily Check-in",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item { FormCard("💫 Today I'm gratefull for", "Write your grateful here...", gratefulFor) { gratefulFor = it } }
            item { FormCard("💫 Daily Mantra", "Write your mantra here...", dailyMantra) { dailyMantra = it } }
            item { FormCard("👥 People I met today", "Who did you interact with?", peopleIMet) { peopleIMet = it } }
            item { FormCard("👥 Things i did", "What did you do?", thingsIDid) { thingsIDid = it } }
            item { FormCard("👥 What I learnt", "....", whatILearnt) { whatILearnt = it } }
            item { FormCard("👥 Things I wanna do tomorrow", "What's on agenda?", thingsTomorrow) { thingsTomorrow = it } }
            
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🌟 What I want to remember", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(bottom = 12.dp))
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.background)
                                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Add Image", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        OutlinedTextField(
                            value = wantToRemember,
                            onValueChange = { wantToRemember = it },
                            placeholder = { Text("Describe the good things...", fontSize = 14.sp) },
                            modifier = Modifier.fillMaxWidth().height(100.dp),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCard(title: String, placeholder: String, value: String, onValueChange: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(placeholder, fontSize = 14.sp) },
                modifier = Modifier.fillMaxWidth().height(100.dp),
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}
