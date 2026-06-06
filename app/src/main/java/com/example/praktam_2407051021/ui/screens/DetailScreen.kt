package com.example.praktam_2407051021.ui.screens

import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.example.praktam_2407051021.viewmodel.JournalViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: JournalViewModel,
    journalId: String
) {
    val note = viewModel.getNoteById(journalId)
    var isFavorite by remember { mutableStateOf(false) }

    if (note == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Journal not found", color = PrimaryText)
        }
        return
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.popBackStack() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Check, contentDescription = "Done")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(24.dp)
        ) {
            item {
                Text(
                    text = "Bloom.ly \uD83C\uDF38",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
                    Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(14.dp), tint = SecondaryText)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(note.date, fontSize = 12.sp, color = SecondaryText)
                }

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(14.dp), tint = SecondaryText)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(note.musicTrack.replace("\n", " - "), fontSize = 12.sp, color = SecondaryText)
                }

                Text(
                    text = "Daily Check-in",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                PinkHeader("-Today i'm gratefull for-")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.gratefulFor,
                    fontSize = 14.sp,
                    color = PrimaryText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Box {
                                AsyncImage(
                                    model = note.imageUrl,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                IconButton(
                                    onClick = { isFavorite = !isFavorite },
                                    modifier = Modifier.align(Alignment.TopEnd)
                                ) {
                                    Icon(
                                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorite",
                                        tint = if (isFavorite) Color.Red else Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            PinkHeader("-Me-")
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = note.mantra,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText,
                                modifier = Modifier.weight(1f)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PinkHeader("-Daily Mantra-", modifier = Modifier.weight(1f))
                                Box(
                                    modifier = Modifier.size(24.dp).background(PinkAccent, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("✨", fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ListCard(title = "-Things I did today-", items = note.thingsIDid, modifier = Modifier.weight(1f))
                    ListCard(title = "-People I met today-", items = note.peopleIMet, modifier = Modifier.weight(1f))
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ListCard(title = "-What I learnt?-", items = note.whatILearnt, modifier = Modifier.weight(1f))
                    ListCard(title = "-Plan for tomorrow-", items = listOf(note.thingsToTomorrow), modifier = Modifier.weight(1f))
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PinkHeader("-What I want to remember?-")
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "\"${note.wantToRemember}\"",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = PrimaryText
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PinkHeader(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(PinkAccent, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
    }
}

@Composable
fun ListCard(title: String, items: List<String>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            PinkHeader(title)
            Spacer(modifier = Modifier.height(12.dp))
            items.forEach { item ->
                Text(
                    text = "• $item",
                    fontSize = 12.sp,
                    color = PrimaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}