package com.example.praktam_2407051021.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val PeacefulColor = Color(0xFFE5B3B3)
val NeutralColor = Color(0xFFE5DECA)
val HeavyColor = Color(0xFFD69BA6)

@Composable
fun MoodHistoryScreen(navController: NavController) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HomeTopBar(
                modifier = Modifier.statusBarsPadding(),
                onProfileClick = { navController.navigate("profile") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Mood History",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = PrimaryText,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "A gentle look back at your emotional landscape over time. Notice the patterns without judgment.",
                    fontSize = 16.sp,
                    color = SecondaryText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                    lineHeight = 24.sp
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        // Month Header
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous", tint = SecondaryText)
                            Text("October 2023", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = PrimaryText)
                            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next", tint = SecondaryText)
                        }

                        // Days of week
                        val days = listOf("S", "M", "T", "W", "T", "F", "S")
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            days.forEach { day ->
                                Text(text = day, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = SecondaryText, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        // Calendar Grid (Dummy Data)
                        val dates = (1..31).toList()
                        // 1st of Oct is Tuesday (index 2)
                        val offset = 2
                        val totalCells = offset + dates.size
                        val rows = (totalCells + 6) / 7

                        for (i in 0 until rows) {
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                                for (j in 0..6) {
                                    val cellIndex = i * 7 + j
                                    val date = cellIndex - offset + 1
                                    
                                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                        if (date in 1..31) {
                                            DateCell(date = date, isSelected = date == 10)
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))
                        Divider(color = MaterialTheme.colorScheme.surfaceVariant)
                        Spacer(modifier = Modifier.height(24.dp))

                        // Legend
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LegendItem("Peaceful", PeacefulColor)
                            LegendItem("Neutral", NeutralColor)
                            LegendItem("Heavy", HeavyColor)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DateCell(date: Int, isSelected: Boolean) {
    // Generate some random mood for display
    val moodColor = when (date % 3) {
        0 -> PeacefulColor
        1 -> NeutralColor
        else -> HeavyColor
    }
    
    val bgModifier = if (isSelected) Modifier.background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp)) else Modifier
    
    Column(
        modifier = Modifier.size(40.dp).then(bgModifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = date.toString(), fontSize = 16.sp, color = PrimaryText)
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier.size(6.dp).background(moodColor, CircleShape))
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(10.dp).background(color, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, fontSize = 12.sp, color = SecondaryText)
    }
}
